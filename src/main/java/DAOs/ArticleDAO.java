package DAOs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Article;
import Model.Categorie;

public class ArticleDAO extends DAO<Article> {

    @Override
    public List<Article> selectAll() {
        List<Article> maListe=new ArrayList<Article>();
        CategorieDAO catDao=new CategorieDAO();

        try
        {
            ResultSet result=this.connect.getConnexion().createStatement().executeQuery("SELECT * FROM articles;");
            while(result.next())
            {
                Article article=new Article(
                        result.getInt(1),
                        result.getString(2),
                        result.getDouble(3),
                        result.getInt(4),
                        catDao.find(result.getInt(5)), // ???
                        result.getString(6)
                );
                maListe.add(article);
            }

        } catch(SQLException e)
        {
            e.printStackTrace();
        }
        return maListe;
    }

    public List<Article> selectAllByCat(String categorie) {
        List<Article> maListe=new ArrayList<Article>();
        CategorieDAO catDao=new CategorieDAO();
        int id=catDao.find(categorie).getRefCat();
        try
        {
            ResultSet result=this.connect.getConnexion().createStatement().executeQuery("SELECT * FROM articles WHERE categorie="+id+";");
            while(result.next())
            {
                Article article=new Article(
                        result.getInt(1),
                        result.getString(2),
                        result.getDouble(3),
                        result.getInt(4),
                        catDao.find(result.getInt(5)), // a refaire
                        result.getString(6)
                );
                maListe.add(article);
            }

        } catch(SQLException e)
        {
            e.printStackTrace();
        }
        return maListe;
    }
    @Override
    public boolean create(Article obj) {

        try
        {	String sql="INSERT INTO articles VALUES("+ obj.getCodeArticle()+",'"+obj.getDesignation()+
                "',"+ obj.getPrix()+","+ obj.getStock()+","+ obj.getCategorie().getRefCat()+","+ obj.getPhoto()+");";
            this.connect.getConnexion().createStatement().executeUpdate(sql);
            return true;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Article obj) {
        // TODO Auto-generated method stub
        try
        {
            //done
            this.connect.getConnexion().createStatement().executeUpdate("DELETE FROM articles WHERE codearticle="+ obj.getCodeArticle() +";");
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Article obj) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Article find(int id) {
        Article article=new Article();
        CategorieDAO catDao=new CategorieDAO();

        try
        {
            ResultSet result=this.connect.getConnexion().createStatement().executeQuery("SELECT * FROM articles WHERE codearticle="+ id+";" );
            while(result.next())
            {
                article=new Article(
                        result.getInt(1),
                        result.getString(2),
                        result.getDouble(3),
                        result.getInt(4),
                        catDao.find(result.getInt(5)),
                        result.getString(6)
                );

            }

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return article;

    }

}