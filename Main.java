import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Main {

	public static TreeMap<String, Integer> rendez(Map<String, Integer> lista) {
		TreeMap<String, Integer> rendezett = new TreeMap<String, Integer>(new Comparator<String>() {
			public int compare(String o1, String o2) {
				int compare = lista.get(o2).compareTo(lista.get(o1));
				if (compare == 0) {
					return 1;
				} else {
					return compare;
				}
			}
		});
		rendezett.putAll(lista);
		return rendezett;
	}

	public static TreeMap<Integer, String> rendezStringet(Map<Integer, String> lista) {
		TreeMap<Integer, String> rendezett = new TreeMap<Integer, String>(new Comparator<Integer>() {
			public int compare(Integer o2, Integer o1) {
				int compare = lista.get(o1).compareTo(lista.get(o2));
				if (compare == 0) {
					return 1;
				} else {
					return compare;
				}
			}
		});
		rendezett.putAll(lista);
		return rendezett;
	}

	public static List<Nobel> beolvas() {
		List<Nobel> lista = new ArrayList<Nobel>();
		try {
			List<String> sorok = Files.readAllLines(Paths.get("nobel.csv"));
			for (String sor : sorok.subList(1, sorok.size())) {
				String[] split = sor.split(";");
				if (split.length == 4) {
					Nobel o = new Nobel(Integer.parseInt(split[0]), split[1], split[2], split[3]);
					lista.add(o);
				} else {
					Nobel o = new Nobel(Integer.parseInt(split[0]), split[1], split[2], "");
					lista.add(o);
				}
			}
		} catch (Exception e) {
			System.out.println("hiba a beolvasásnál. " + e);
		}
		return lista;
	}

	public static void main(String[] args) throws IOException {
		List<Nobel> a = beolvas();

		List<Nobel> beke = new ArrayList<Nobel>();
		String irod2017 = "";
		for (Nobel n : a) {
			if (n.teljesNev().equals("Arthur B. McDonald")) {
				System.out.println("3. feladat: " + n.getTipus());
			}
			if (n.getEvszam() == 2017 && n.getTipus().equals("irodalmi")) {
				irod2017 = n.teljesNev();
			}
			if (n.getVezeteknev().equals("") && n.getEvszam() > 1989 && n.getTipus().contains("béke")) {
				beke.add(n);
			}
		}
		System.out.println("4. feladat: " + irod2017);
		// 5. feladat:
		System.out.println("5. feladat: ");

		for (Nobel s : beke) {
			System.out.println("\t" + s.getEvszam() + ": " + s.teljesNev());
		}
		System.out.println("5. feladat: ");
		HashMap<String, Integer> stat = new HashMap<String, Integer>();

		for (Nobel nobel : a) {
			if (nobel.getVezeteknev().contains("Curie")) {
				System.out.println("\t" + nobel.getEvszam() + ": " + nobel.teljesNev() + "(" + nobel.getTipus() + ")");
			}
			stat.merge(nobel.getTipus(), 1, Integer::sum);
		}
		System.out.println("7. feladat: ");

		TreeMap<String, Integer> rendezett = rendez(stat);

		for (Entry<String, Integer> e : rendezett.entrySet()) {
			System.out.println("\t" + e.getKey() + " - " + e.getValue());
		}

		String fajlba = "";

		for (Nobel nobel : a) {
			if (nobel.getTipus().contains("orvosi")) {
				fajlba += nobel.getEvszam() + ":" + nobel.teljesNev() + "\n";
			}
		}
		Files.write(Paths.get("orvosi.txt"), fajlba.getBytes());

		System.out.println();
		System.out.println(
				"Itt van rendezve ZS-A-ig: ugyan úgy o1-o2-vel kell megfordítani. Nyilván bele kellett nyúlni a 'rendez' függvénybe, ezért lett 'rendezStringet'. Nyilván az évszámok ilyenkor össze vissza vannak. ");
		// Turcsányi kedvéért: szeretné, ha az orvosok zs-a sorrendben jelennének meg.
		String fajlba2 = "";
		HashMap<Integer, String> fileba = new HashMap<Integer, String>();
		for (Nobel n : a) {
			if (n.getTipus().equals("orvosi")) {
				fileba.put(n.getEvszam(), n.teljesNev());
			}
		}

		TreeMap<Integer, String> turcsanyinak = rendezStringet(fileba);
		turcsanyinak.forEach((k, v) -> {
			System.out.println("\t" + k + ' ' + v);
		});
		;

	}// endofmain
}
