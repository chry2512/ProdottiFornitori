package epicode.it.model;

public class FornitoreDTO {

	
	private int id;
	private String nome;
	private String indirizzo;
	private String citta;

	public FornitoreDTO() {
		
	}
	
	public FornitoreDTO(int id, String nome, String indirizzo, String citta) {
		super();
		this.id = id;
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.citta = citta;
	}

	
	

	public int getId() {
		return id;
	}

	public FornitoreDTO setId(int id) {
		this.id = id;
		return this;
	}

	public String getNome() {
		return nome;
	}

	public FornitoreDTO setNome(String nome) {
		this.nome = nome;
		return this;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public FornitoreDTO setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
		return this;
	}

	public String getCitta() {
		return citta;
	}

	public FornitoreDTO setCitta(String citta) {
		this.citta = citta;
		return this;
	}

	@Override
	public String toString() {
		return "FornitoreDAO [id=" + id + ", nome=" + nome + ", citta=" + citta + "]";
	}

}
