import com.github.javaparser.*;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.*;
import com.github.javaparser.utils.SourceRoot;
import java.nio.file.*;
import java.util.*;

public class Remap {
  // (classFQN|obfName|argc) -> realName
  static Map<String,String> map = new HashMap<>();
  static int renamed=0, resolvedCalls=0, unresolved=0;

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
    sr.tryToParse();
    for (CompilationUnit cu : sr.getCompilationUnits()) {
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
            }
          } catch (Throwable t){ unresolved++; }
        }
      }, null);
    }
    sr.saveAll();
    System.out.printf("resolved-call-scopes=%d unresolved=%d  methods-renamed=%d%n",
        resolvedCalls, unresolved, renamed);
  }
}
