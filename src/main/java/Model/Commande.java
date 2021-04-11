package Model;

import java.util.Date;

public class Commande {
    private int numCommande;
    private Date dateCommande;
    private Client client;

    public Commande() {
        super();
    }

    public Commande(Client client, Date dateCommande) {
        super();
        this.client = client;
        this.dateCommande = dateCommande;
    }

    public Commande(int numCommande, Client client, Date dateCommande) {
        super();
        this.numCommande = numCommande;
        this.client = client;
        this.dateCommande = dateCommande;
    }

    public int getNumCommande() {
        return numCommande;
    }

    public void setNumCommande(int numCommande) {
        this.numCommande = numCommande;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}