// Triangle class
// defines a Triangle shape

import javax.vecmath.*;

public class Triangle extends Shape {
	private Vector3f p0, p1, p2;	// three vertices make a triangle
	private Vector3f n0, n1, n2;	// normal at each vertex

	public Triangle() {
	}
	public Triangle(Vector3f _p0, Vector3f _p1, Vector3f _p2, Material mat) {
		p0 = new Vector3f(_p0);
		p1 = new Vector3f(_p1);
		p2 = new Vector3f(_p2);
		material = mat;
		Vector3f normal = new Vector3f();
		Vector3f v1 = new Vector3f();
		Vector3f v2 = new Vector3f();
		v1.sub(p1, p0);
		v2.sub(p2, p0);
		normal.cross(v1, v2);
		normal.normalize();				// compute default normal:
		n0 = new Vector3f(normal);		// the normal of the plane defined by the triangle
		n1 = new Vector3f(normal);
		n2 = new Vector3f(normal);
	}
	public Triangle(Vector3f _p0, Vector3f _p1, Vector3f _p2,
					Vector3f _n0, Vector3f _n1, Vector3f _n2,
					Material mat) {
		p0 = new Vector3f(_p0);
		p1 = new Vector3f(_p1);
		p2 = new Vector3f(_p2);
		material = mat;
		n0 = new Vector3f(_n0);		// the normal of the plane defined by the triangle
		n1 = new Vector3f(_n1);
		n2 = new Vector3f(_n2);
	}
	public HitRecord hit(Ray ray, float tmin, float tmax) {
                Vector3f pn = new Vector3f();
		Vector3f p1_p0 = new Vector3f(p1.x - p0.x, p1.y - p0.y, p1.z - p0.z);
                Vector3f p2_p0 = new Vector3f(p2.x - p0.x, p2.y - p0.y, p2.z - p0.z);
                pn.cross(p1_p0, p2_p0);
                pn.normalize();
                Shape plane = new Plane(p0, pn, material);
                HitRecord hit = plane.hit(ray, tmin, tmax);
                if (hit == null) {
                    return null;
                }
		return hit;
	}
}
