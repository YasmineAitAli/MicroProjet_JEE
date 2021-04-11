package DAOs;

import java.util.List;

import Database.ConnectionDB;

public abstract class DAO<T> {

    ConnectionDB connect=null;

    public DAO(){
        this.connect =ConnectionDB.getInstance();
    }


    public abstract List<T> selectAll();
    public abstract boolean create(T obj);
    public abstract boolean delete(T obj);
    public abstract boolean update(T obj);
    public abstract T find(int id);

}