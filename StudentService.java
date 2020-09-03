/***
Aditya Varma Vetukuri
Sai Ruchieatha Maanvi Thibirisetti
Sai Venkata Sucheta Pallempati
Kaushik Gedela
Jeevan Mamillapalli
***/

package managedBeans;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/***
--> Description: for calculating mean and standard deviation. 
***/

public class StudentService implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * @param stu
	 * @return
	 */
	public static final EntityManagerFactory emfact = Persistence.createEntityManagerFactory("student");
	EntityManager em;

	/**
	 * @return
	 */
	public static EntityManager getEntityManager() {
		EntityManager entityManager = emfact.createEntityManager();
		return entityManager;
	}

	
	public static void studentsData(Student stu) {

		EntityManager etm = getEntityManager();
		etm.getTransaction().begin();
		etm.persist(stu);
		etm.getTransaction().commit();
		etm.close();

	}
	
	public double calculateMean(Student stu) {
		String r[] = (stu.getRaffle()).split(",");

		int sum = 0;

		int k;
		for (k = 0; k < r.length; k++) {
			int c = Integer.parseInt(r[k]);
			sum += c;
		}

		return sum / k;

	}

	/**
	 * @param stu
	 * @return
	 */
	public double calculateSDeviation(Student stu) {
		String r[] = (stu.getRaffle()).split(",");
		int raffleTicket[] = new int[20];
		int m = 0;
		int sum = 0;
		double deviation;
		for (String string : r) {
			raffleTicket[m] = Integer.parseInt(string);
			m++;
		}

		double mean = calculateMean(stu);
		for (int k = 0; k < r.length; k++) {
			sum += Math.pow((raffleTicket[k] - mean), 2);

		}
		deviation = Math.pow(sum / (r.length - 1), 1 / 2);

		return deviation;
	}

} 
