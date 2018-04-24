// Sphere class
// defines a Sphere shape

import javax.vecmath.*;

public class Sphere extends Shape {
	private Vector3f center;	// center of sphere
	private float radius;		// radius of sphere

	public Sphere() {
	}
	public Sphere(Vector3f pos, float r, Material mat) {
		center = new Vector3f(pos);
		radius = r;
		material = mat;
	}
	public HitRecord hit(Ray ray, float tmin, float tmax) {
		/* compute ray-plane intersection */
                Vector3f origin = ray.getOrigin();
                Vector3f direction = ray.getDirection();
		Vector3f temp = new Vector3f(origin.x - center.x, origin.y - center.y, origin.z - center.z);
                //Calculate quadratic variables (a*t^2 + b*t + t = r^2)
                float a = direction.dot(direction);
                float b = 2.0f * temp.dot(direction);
                float c = temp.dot(temp) - radius * radius;
                float discriminant = (float) (b * b - 4 * a * c);
		if (discriminant <= 0.f) {
                    return null;
                }
		double t = ((b * -1) - Math.sqrt(discriminant)) / (2 * a);
		if (t < tmin)
                    t = ((b * -1) + Math.sqrt(discriminant)) / (2 * a);
                /* if t out of range, return null */
                if (t < tmin || t > tmax)	
                    return null;
		/* construct hit record */
		HitRecord rec = new HitRecord();
		rec.pos = ray.pointAt((float) t);		// position of hit point
		rec.t = (float) t;						// parameter t (distance along the ray)
		rec.material = material;		// material
		rec.normal = new Vector3f(origin.x + (float) (t * direction.x) - center.x, origin.y + (float) (t * direction.y) - center.y, origin.z + (float) (t * direction.z) - center.z);
                rec.normal.normalize();			// normal should be normalized
		return rec;
	}
}
