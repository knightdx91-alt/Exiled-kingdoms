import com.github.javaparser.*;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.*;
import com.github.javaparser.utils.SourceRoot;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;
import java.nio.file.*;
import java.util.*;

public class Remap {
  // (classFQN|obfName|argc) -> realName
  static Map<String,String> map = new HashMap<>();
  static int renamed=0, resolvedCalls=0, unresolved=0;
  static Map<String,Integer> unmapped=new TreeMap<>();

  public static void main(String[] a) throws Exception {
    Path srcRoot = Paths.get(a[0]);
    String gdxJar = a[1];
    for (String line : Files.readAllLines(Paths.get(a[2]))) {
      String[] p = line.split("\t");
      if (p.length>=4) map.put(p[0]+"|"+p[1]+"|"+p[2], p[3]);
    }
    CombinedTypeSolver ts = new CombinedTypeSolver();
    ts.add(new ReflectionTypeSolver(false));
    ts.add(new JarTypeSolver(gdxJar));
    ts.add(new JavaParserTypeSolver(srcRoot));
    ParserConfiguration cfg = new ParserConfiguration()
        .setSymbolResolver(new JavaSymbolSolver(ts))
        .setLanguageLevel(ParserConfiguration.LanguageLevel.JAVA_11);
    SourceRoot sr = new SourceRoot(srcRoot, cfg);
    List<ParseResult<CompilationUnit>> results = sr.tryToParse("");
    for (ParseResult<CompilationUnit> pr : results) {
      if (!pr.isSuccessful() || pr.getResult().isEmpty()) continue;
      CompilationUnit cu = pr.getResult().get();
      LexicalPreservingPrinter.setup(cu);
      int before = renamed;
      cu.accept(new VoidVisitorAdapter<Void>(){
        @Override public void visit(MethodCallExpr m, Void v){
          super.visit(m,v);
          String name=m.getNameAsString();
          if (!name.matches("[a-z][0-9]?")) return; // only obfuscated
          if (m.getScope().isEmpty()) return;
          try {
            ResolvedType rt = m.getScope().get().calculateResolvedType();
            resolvedCalls++;
            if (!rt.isReferenceType()) return;
            String fqn = rt.asReferenceType().getQualifiedName();
            String key = fqn+"|"+name+"|"+m.getArguments().size();
            String real = map.get(key);
            if (real!=null && !real.equals(name) && real.matches("[A-Za-z_]\\w*")){
              m.setName(real); renamed++;
            } else if (real==null && fqn.startsWith("com.badlogic.gdx")) {
              unmapped.merge(fqn+"#"+name+"#"+m.getArguments().size(),1,Integer::sum);
            }
          } catch (Throwable t){ unresolved++; }
        }
      }, null);
      if (renamed>before && cu.getStorage().isPresent()) {
        java.nio.file.Path pth = cu.getStorage().get().getPath();
        Files.write(pth, LexicalPreservingPrinter.print(cu).getBytes());
      }
    }
    System.out.printf("resolved-call-scopes=%d unresolved=%d  methods-renamed=%d%n",
        resolvedCalls, unresolved, renamed);
    StringBuilder sb=new StringBuilder();
    unmapped.entrySet().stream().sorted((x,y)->y.getValue()-x.getValue())
      .forEach(e->sb.append(e.getValue()).append("\t").append(e.getKey()).append("\n"));
    Files.write(Paths.get("/tmp/unmapped.tsv"), sb.toString().getBytes());
  }
}
