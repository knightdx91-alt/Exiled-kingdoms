// Upright map tile (tree, rock, object) as a Y-billboard card — deobf/QUEST_3D_TUTORIAL_SPEC.md §2.
// One MultiMesh per tileset: the instance transform carries the cell position and the
// card's world size (x = width, y = height); INSTANCE_CUSTOM is the tile's atlas rect.
shader_type spatial;
render_mode unshaded, cull_disabled, depth_draw_opaque;

uniform sampler2D atlas : source_color, filter_linear_mipmap;
uniform vec3 viewer_pos;      // head position, world space
uniform vec3 focus;           // board centre (the hero), world space
uniform float radius = 1.0;   // visible disc radius, world units
uniform vec3 tint = vec3(0.93);

varying float rim;

void vertex() {
	vec3 base = (MODEL_MATRIX * vec4(0.0, 0.0, 0.0, 1.0)).xyz;
	vec3 up = MODEL_MATRIX[1].xyz;                       // world up * card height
	vec3 upn = normalize(up);
	float w = length(MODEL_MATRIX[0].xyz);
	vec3 to = viewer_pos - base;
	to -= dot(to, upn) * upn;                            // face the viewer around the vertical
	vec3 right = normalize(cross(upn, normalize(to + vec3(1e-5))));
	vec3 world = base + right * VERTEX.x * w + up * VERTEX.y;
	vec3 d = base - focus;
	d -= dot(d, upn) * upn;
	rim = length(d) / radius;
	UV = INSTANCE_CUSTOM.xy + UV * INSTANCE_CUSTOM.zw;
	POSITION = PROJECTION_MATRIX * VIEW_MATRIX * vec4(world, 1.0);
}

void fragment() {
	if (rim > 1.0) discard;
	vec4 c = texture(atlas, UV);
	ALBEDO = c.rgb * tint * mix(1.0, 0.55, smoothstep(0.8, 1.0, rim));
	ALPHA = c.a;
	ALPHA_SCISSOR_THRESHOLD = 0.5;
}
