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
                Vector3f origin = ray.getOrigin();
		Vector3f direction = ray.getDirection();
                Matrix3f matrix = new Matrix3f(direction.x, p0.x - p1.x, p0.x - p2.x, direction.y, p0.y - p1.y, p0.y - p2.y, direction.z, p0.z - p1.z, p0.z - p2.z);
                Matrix3f beta_matrix = new Matrix3f(direction.x, p0.x - origin.x, p0.x - p2.x, direction.y, p0.y - origin.y, p0.y - p2.y, direction.z, p0.z - origin.z, p0.z - p2.z);
                Matrix3f gamma_matrix = new Matrix3f(direction.x, p0.x - p1.x, p0.x - origin.x, direction.y, p0.y - p1.y, p0.y - origin.y, direction.z, p0.z - p1.z, p0.z - origin.z);
                Matrix3f t_matrix = new Matrix3f(p0.x - origin.x, p0.x - p1.x, p0.x - p2.x, p0.y - origin.y, p0.y - p1.y, p0.y - p2.y, p0.z - origin.z, p0.z - p1.z, p0.z - p2.z);
                float beta = (float) beta_matrix.determinant() / matrix.determinant();
                float gamma = (float) gamma_matrix.determinant() / matrix.determinant();
                float t = (float) t_matrix.determinant() / matrix.determinant();
                if (t < tmin || t > tmax || beta < 0 || gamma < 0 || beta + gamma > 1) {
                    return null;
                }
                HitRecord rec = new HitRecord();
                rec.pos = ray.pointAt(t);		// position of hit point
                rec.t = t;						// parameter t (distance along the ray)
                rec.material = material;		// material
                rec.normal = new Vector3f(n0);					// normal at the hit point
                rec.normal.normalize();			// normal should be normalized
                return rec;
        }
}
