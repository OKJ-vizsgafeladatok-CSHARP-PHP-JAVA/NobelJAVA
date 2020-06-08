
public class Nobel {

	private int evszam;
	private String tipus;
	private String keresztnev;
	private String vezeteknev;
	public Nobel(int evszam, String tipus, String keresztnev, String vezeteknev) {
		super();
		this.evszam = evszam;
		this.tipus = tipus;
		this.keresztnev = keresztnev;
		this.vezeteknev = vezeteknev;
	}
	public int getEvszam() {
		return evszam;
	}
	public void setEvszam(int evszam) {
		this.evszam = evszam;
	}
	public String getTipus() {
		return tipus;
	}
	public void setTipus(String tipus) {
		this.tipus = tipus;
	}
	public String getKeresztnev() {
		return keresztnev;
	}
	public void setKeresztnev(String keresztnev) {
		this.keresztnev = keresztnev;
	}
	public String getVezeteknev() {
		return vezeteknev;
	}
	public void setVezeteknev(String vezeteknev) {
		this.vezeteknev = vezeteknev;
	}
	@Override
	public String toString() {
		return "Nobel [evszam=" + evszam + ", tipus=" + tipus + ", keresztnev=" + keresztnev + ", vezeteknev="
				+ vezeteknev + "]";
	}
	
	public String teljesNev() {
		return keresztnev+" "+vezeteknev;
	}
}
