package dao;

import dao.custom.customerDAO;
import dao.custom.impl.customerDAOimpl;
import dao.custom.impl.itemDAOimpl;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){


    }

    public static DAOFactory getInstance(){
        if(daoFactory==null){
            daoFactory=new DAOFactory();
        }

        return daoFactory;

    }

   public Object getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER:
                return  new customerDAOimpl();
            case ITEM:
                return  new itemDAOimpl();
            default:
                return null;
        }

   }

}
