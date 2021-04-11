package DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Client;
import Model.Achat;

public class ClientDAO extends DAO<Client> {

    @Override
    public List<Client> selectAll() {
        {
            List<Client> liste =  new ArrayList<Client>() ;
            try
            {
                ResultSet result=this.connect.getConnexion().createStatement().executeQuery("SELECT * FROM client");
                while(result.next())
                {
                    Client client=new Client(result.getInt("id"),result.getString("email"),result.getString("nom"),result.getString("prenom"),result.getString("adresse"),result.getString("codepostal"),result.getString("ville"),result.getString("tel"),result.getString("mot_de_passe"));
                    liste.add(client);
                }

            }
            catch(SQLException e )
            {
                e.printStackTrace();
            }
            return liste;

        }
    }

    public List<Achat> selectAllbyClient(int id) {
        List<Achat> liste =  new ArrayList<Achat>() ;

        try
        {
            ResultSet result=this.connect.getConnexion().createStatement().executeQuery(""
                    + "SELECT a.codearticle,a.designation,a.prix,l.qtecde,c.datecommande FROM lignescommandes l,articles a,commandes c WHERE a.codearticle=l.codearticle AND l.numcommande=c.numcommande AND c.codeclient="+id+";");

            while(result.next())
            {
                Achat achat=new Achat(
                        result.getInt(1),
                        result.getString(2),
                        result.getDouble(3),
                        result.getInt(4),
                        result.getInt(4)*result.getDouble(3),
                        result.getDate(5)
                );
                liste.add(achat);
            }

        }
        catch(SQLException e )
        {
            e.printStackTrace();
        }
        return liste;
    }



    @Override
    public boolean create(Client X) {

        String emailX=X.getEmail();
        String prenomX=X.getPrenom();
        String nomX=X.getNom();
        String adresseX=X.getAdresse();
        String telX=X.getTel();
        String codePostalX=X.getCodePostal();
        String villeX=X.getVille();
        String motDePasseX=X.getMotDePasse();
        try {
            this.connect.getConnexion().createStatement().executeUpdate("INSERT INTO clients(email,nom,prenom,adresse,codePostal,ville,tel,motDePasse) VALUES('"+emailX+"','"+nomX+"','"+prenomX+"','"+adresseX+"','"+codePostalX+"','"+villeX+"','"+telX+"','"+motDePasseX+"');");

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
        return true;

    }

    @Override
    public boolean delete(Client obj) {
        int id2=obj.getId();
        String sql= "DELETE FROM client WHERE id="+id2+" ";
        try {
            PreparedStatement p = this.connect.getConnexion().prepareStatement(sql);
            p.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean update(Client obj) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Client find(int id) {
        Client client=null;
        try {
            ResultSet result=this.connect.getConnexion().createStatement().executeQuery("SELECT * FROM clients WHERE id ="+ id +";");
            if(result.next()){
                client = new Client (id, result.getString("email"), result.getString("nom"),result.getString("prenom"),result.getString("adresse"),result.getString("codepostal"), result.getString("ville"),result.getString("tel"),result.getString("motdepasse"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;

    }

    public Client find(String email) {
        Client client=null;
        try {
            ResultSet result=this.connect.getConnexion().createStatement().executeQuery("SELECT * FROM clients WHERE email='"+ email +"';");
            if(result.next()){
                client = new Client (result.getInt(1), result.getString("email"), result.getString("nom"),result.getString("prenom"),result.getString("adresse"),result.getString("codepostal"), result.getString("ville"),result.getString("tel"),result.getString("motdepasse"));

            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return client;

    }
}