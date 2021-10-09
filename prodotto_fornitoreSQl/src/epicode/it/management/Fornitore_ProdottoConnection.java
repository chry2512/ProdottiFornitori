package epicode.it.management;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import epicode.it.model.FornitoreDTO;
import epicode.it.model.ProdottoDTO;

public class Fornitore_ProdottoConnection extends ConnectionPostgres {
	private static final Logger logger = LoggerFactory.getLogger(Fornitore_ProdottoConnection.class);

	private static final String dbName = "postgres";
	private static final String schema = "negozio";
	private static final String username = "postgres";
	private static final String password = "ciao";

	public Fornitore_ProdottoConnection(String host, int port) {
		super(host, port, dbName, schema, username, password);

	}

	private static class FornitoriCommands {
		public static final String INSERT = "INSERT INTO fornitore(id_fornitore, nome, indirizzo, citta) VALUES(?,?,?,?);";
		public static final String UPDATE = "UPDATE fornitore SET nome = ?, indirizzo = ?, citta = ? WHERE id_fornitore = ?;";
		public static final String DELETE = "DELETE FROM fornitore WHERE id_fornitore= ?;";
		public static final String SELECT_BY_ID = "SELECT id_fornitore, nome, indirizzo, citta FROM fornitore WHERE id_fornitore = ? ;";
		public static final String SELECT_BY_CITY = "SELECT id_fornitore, nome, indirizzo, citta FROM fornitore WHERE citta = ?  ;";
		public static final String SELECT_ALL = "SELECT id_fornitore, nome, indirizzo, citta FROM fornitore ORDER BY nome;";
	}

	private static class ProdottiCommands {
		public static final String INSERT = "INSERT INTO prodotto(id_prodotto, nome, descrizione, marca, id_fornitore, prezzo) VALUES(?,?,?,?,?,?);";
		public static final String SELECT_ALL_PRODOTTO = "SELECT id_prodotto, nome, marca, descrizione, prezzo, id_fornitore FROM prodotto ORDER BY nome;";
		public static final String SELECT_ALL_BY_FORNITORE = "SELECT pr.id_prodotto, pr.nome, pr.marca, descrizione, prezzo, id_fornitore,  for.nome "
				+ "FROM negozio.prodotto pr "
				+ "	INNER JOIN negozio.fornitore for ON pr.id_fornitore = for.id_fornitore " + " ORDER BY pr.nome;";
	}

	public void save(FornitoreDTO for1) throws SQLException {
		var smt = getPreparedStatement(FornitoriCommands.INSERT);
		smt.setInt(1, for1.getId());
		smt.setString(2, for1.getNome());
		smt.setString(3, for1.getIndirizzo());
		smt.setString(4, for1.getCitta());
		smt.executeUpdate();
	}

	public void update(int forId, FornitoreDTO for1) throws SQLException {
		var smt = getPreparedStatement(FornitoriCommands.UPDATE);
		smt.setString(1, for1.getNome());
		smt.setString(2, for1.getIndirizzo());
		smt.setString(3, for1.getCitta());
		smt.setInt(4, for1.getId());
		smt.executeUpdate();
	}

	public void delete(int forId) throws SQLException {
		var smt = getPreparedStatement(FornitoriCommands.DELETE);
		smt.setInt(1, forId);
		smt.executeUpdate();
	}

	public List<FornitoreDTO> getAll() throws SQLException {
		var smt = getPreparedStatement(FornitoriCommands.SELECT_ALL);
		var result = new ArrayList<FornitoreDTO>();
		try (var rs = smt.executeQuery()) {
			while (rs.next()) {
				result.add(new FornitoreDTO()
						.setId(rs.getInt(1))
						.setNome(rs.getString(2))
						.setIndirizzo(rs.getString(3))
						.setCitta(rs.getString(4)));

			}
		} catch (Exception e) {
			logger.error("Error");
		}
		return result;
	}

	public Optional <FornitoreDTO> getCity(String citta) throws SQLException {
		var smt = getPreparedStatement(FornitoriCommands.SELECT_BY_CITY);
		smt.setString(1, citta);
        
		try (var rs = smt.executeQuery()) {
			if (rs.next()) {
				
				return Optional.of(new FornitoreDTO()
						.setId(rs.getInt(1))
						.setNome(rs.getString(2))
						.setIndirizzo(rs.getString(3))
						.setCitta(rs.getString(4)));

			}
		} catch (Exception e) {
			logger.error("Error");
		}
		return Optional.empty();
	}

	public Optional<FornitoreDTO> getId(int forId) throws SQLException {
		var smt = getPreparedStatement(FornitoriCommands.SELECT_BY_ID);
		smt.setInt(1, forId);

		try (ResultSet rs = smt.executeQuery()) {
			if (rs.next()) {
				return Optional.of(new FornitoreDTO()
						.setId(rs.getInt(1))
						.setNome(rs.getString(2))
						.setIndirizzo(rs.getString(3))
						.setCitta(rs.getString(4)));

			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
		return Optional.empty();
	}

	public void addProdotto(ProdottoDTO pr) throws SQLException {
		var smt = getPreparedStatement(ProdottiCommands.INSERT);
		smt.setInt(1, pr.getId());
		smt.setString(2, pr.getNome());
		smt.setString(3, pr.getDescrizione());
		smt.setString(4, pr.getMarca());
		smt.setInt(5, pr.getIdFornitore());
		smt.setDouble(6, pr.getPrezzo());
		smt.executeUpdate();
	}

	public List<ProdottoDTO> getProdottiByFornitore(String forn) throws SQLException {
		PreparedStatement smt = getPreparedStatement(ProdottiCommands.SELECT_ALL_BY_FORNITORE);
		smt.setString(1, forn);
		List<ProdottoDTO> result = new ArrayList<>();
		try (ResultSet rs = smt.executeQuery()) {
			while (rs.next()) {
				result.add(new ProdottoDTO()
						.setId(rs.getInt(1))
						.setNome(rs.getString(2))
						.setMarca(rs.getString(3))
						.setDescrizione(rs.getString(4))
						.setPrezzo(rs.getDouble(5))
						.setIdFornitore(rs.getInt(6)));

			}

		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
		return result;
	}
	
	public List<ProdottoDTO> getAllPr() throws SQLException {
		PreparedStatement smt = getPreparedStatement(ProdottiCommands.SELECT_ALL_PRODOTTO);
		List<ProdottoDTO> result = new ArrayList<ProdottoDTO>();
		try (ResultSet rs = smt.executeQuery()) {
			while (rs.next()) {
				result.add(new ProdottoDTO()
						.setId(rs.getInt(1))
						.setNome(rs.getString(2))
						.setMarca(rs.getString(3))   
						.setDescrizione(rs.getString(4))
						.setPrezzo(rs.getDouble(5))
						.setIdFornitore(rs.getInt(6)));
			}
		
		} catch (Exception e) {
			logger.error("Error");
		}
		return result;
	}


}
