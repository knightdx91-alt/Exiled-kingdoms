// The baked iso floor on one ground quad. UVs are the (affine) iso projection, so
// the quad shows the original floor art exactly — deobf/QUEST_3D_TUTORIAL_SPEC.md §2.
shader_type spatial;
render_mode unshaded, cull_disabled;

uniform sampler2D floor_tex : source_color, filter_linear_mipmap;
uniform vec3 focus;
uniform float radius = 1.0;
uniform vec3 tint = vec3(0.93);

varying vec3 wpos;

void vertex() {
	wpos = (MODEL_MATRIX * vec4(VERTEX, 1.0)).xyz;
}

void fragment() {
	vec2 d = wpos.xz - focus.xz;
	float rim = length(d) / radius;
	if (rim > 1.0) discard;
	vec3 c = texture(floor_tex, UV).rgb;
	ALBEDO = c * tint * mix(1.0, 0.45, smoothstep(0.8, 1.0, rim));
}
