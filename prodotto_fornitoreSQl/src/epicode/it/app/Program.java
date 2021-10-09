package epicode.it.app;

import java.util.Optional;

import epicode.it.management.Fornitore_ProdottoConnection;
import epicode.it.model.FornitoreDTO;
import epicode.it.model.ProdottoDTO;

public class Program {

	public static void main(String[] args) {
		try (Fornitore_ProdottoConnection h = new Fornitore_ProdottoConnection("localhost", 5432)) {

			// Aggiungi Fonitore
			// FornitoreDTO f1 = new FornitoreDTO(3, "ipercoop", "via dei mille",
			// "firenze");
			// h.save(f1);

			// Elenco Fonitori
			System.out.println("================");
			System.out.println("Elenco Fornitori");
			h.getAll().stream().forEach(a -> System.out.println(a));

			// Trova Fornitore by Id
			System.out.println("===============");
			System.out.println("Elenco Prodotti");
			System.out.println("==========================");
			System.out.println("Trova Fornitore tramite Id");
			h.getId(2).stream().forEach(a -> System.out.println(a));

			// Aggiungi Prodotto
			// ProdottoDTO p1 = new ProdottoDTO(1, "fiesta", "merendine", "ferrero", 3,
			// 4.0);
			// h.addProdotto(p1);

			// Elenco Prodotti
			System.out.println("===============");
			System.out.println("Elenco Prodotti");
			h.getAllPr().stream().forEach(a -> System.out.println(a));

			// Elimina Fornitore
			System.out.println("=======");
			System.out.println("Elimina");
			h.delete(1);
			h.getAll().stream().forEach(a -> System.out.println(a));

			// Trova Fornitore per Città
			System.out.println("===============");
			System.out.println("Trova per Città");
			System.out.println("===============");
			
			h.getCity("firenze").ifPresentOrElse(a -> System.out.println(a),
					() -> System.out.println("Fornitore non Trovato"));

			Optional<FornitoreDTO> for1 = h.getCity("firenze");

			if (for1.isPresent()) {

				System.out.println("Il Fornitore Trovato è : " + for1);

			} else {
				System.out.println("Nessun Fornitore Trovato");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
