// Free-floating virtual joystick (mobile-friendly, optional control scheme).
//
// "Free-floating" = there is no fixed pad. Wherever the player first touches the play
// area, the joystick base spawns under the finger; the thumb tracks the finger within
// a radius; releasing hides it. It reports a screen-space unit vector (with a dead
// zone) that the engine turns into hero movement. Lives on its own pointer layer that
// is only active in joystick mode, so it never fights Phaser's tap-to-move input.

const RADIUS = 60;         // px: thumb travel that maps to full-speed
const DEAD = 0.18;         // dead zone (fraction of RADIUS) to ignore tiny jitters

export class Joystick {
  constructor(root) {
    this.vec = { x: 0, y: 0 };   // screen-space, magnitude 0..1
    this.active = false;
    this._id = null;

    this.layer = document.createElement('div');
    this.layer.id = 'stick-layer';
    this.layer.style.display = 'none';

    this.base = document.createElement('div'); this.base.className = 'stick-base';
    this.thumb = document.createElement('div'); this.thumb.className = 'stick-thumb';
    this.base.appendChild(this.thumb);
    this.base.style.display = 'none';
    this.layer.appendChild(this.base);
    root.appendChild(this.layer);

    this.layer.addEventListener('pointerdown', (e) => this._down(e));
    this.layer.addEventListener('pointermove', (e) => this._move(e));
    this.layer.addEventListener('pointerup', (e) => this._up(e));
    this.layer.addEventListener('pointercancel', (e) => this._up(e));
    this.layer.addEventListener('pointerleave', (e) => this._up(e));
  }

  enable(on) {
    this.layer.style.display = on ? 'block' : 'none';
    if (!on) this._up({ pointerId: this._id });
  }

  _down(e) {
    if (this._id !== null) return;
    this._id = e.pointerId;
    this.active = true;
    this._ox = e.clientX; this._oy = e.clientY;
    this.base.style.left = `${e.clientX}px`;
    this.base.style.top = `${e.clientY}px`;
    this.base.style.display = 'block';
    this._setThumb(0, 0);
    try { this.layer.setPointerCapture(e.pointerId); } catch {}
  }

  _move(e) {
    if (e.pointerId !== this._id) return;
    let dx = e.clientX - this._ox, dy = e.clientY - this._oy;
    const len = Math.hypot(dx, dy) || 1;
    const clamped = Math.min(len, RADIUS);
    const ux = dx / len, uy = dy / len;
    this._setThumb(ux * clamped, uy * clamped);
    const mag = clamped / RADIUS;
    if (mag < DEAD) { this.vec.x = 0; this.vec.y = 0; }
    else { this.vec.x = ux * mag; this.vec.y = uy * mag; }
  }

  _up(e) {
    if (e && e.pointerId !== this._id) return;
    this._id = null;
    this.active = false;
    this.vec.x = 0; this.vec.y = 0;
    this.base.style.display = 'none';
  }

  _setThumb(x, y) { this.thumb.style.transform = `translate(${x}px, ${y}px)`; }
}
