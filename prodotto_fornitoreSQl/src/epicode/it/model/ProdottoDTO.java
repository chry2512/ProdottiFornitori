package epicode.it.model;

public class ProdottoDTO {

	private int id;
	private String nome;
	private String descrizione;
	private String marca;
	private int idFornitore;
	private double prezzo;

	public ProdottoDTO() {
	}

	public ProdottoDTO(int id, String nome, String descrizione, String marca, int idFornitore, double prezzo) {
		super();
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.marca = marca;
		this.idFornitore = idFornitore;
		this.prezzo = prezzo;
	}

	public int getId() {
		return id;
	}

	public ProdottoDTO setId(int id) {
		this.id = id;
		return this;
	}

	public String getNome() {
		return nome;
	}

	public ProdottoDTO setNome(String nome) {
		this.nome = nome;
		return this;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public ProdottoDTO setDescrizione(String descrizione) {
		this.descrizione = descrizione;
		return this;
	}

	public String getMarca() {
		return marca;
	}

	public ProdottoDTO setMarca(String marca) {
		this.marca = marca;
		return this;
	}

	public int getIdFornitore() {
		return idFornitore;
	}

	public ProdottoDTO setIdFornitore(int idFornitore) {
		this.idFornitore = idFornitore;
		return this;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public ProdottoDTO setPrezzo(double prezzo) {
		this.prezzo = prezzo;
		return this;
	}

	@Override
	public String toString() {
		return "ProdottoDAO [id=" + id + ", nome=" + nome + ", descrizione=" + descrizione + ", marca=" + marca
				+ ", idFornitore=" + idFornitore + ", prezzo=" + prezzo + "]";
	}

}
