package business;

import dao.custom.impl.itemDAOimpl;
import dto.itemDTO;
import entity.item;

import java.sql.SQLException;

public class itemBO {



    public boolean SaveItem(itemDTO dto) throws SQLException, ClassNotFoundException {
        itemDAOimpl dao = new itemDAOimpl();
        return dao.SaveItem(new item(dto.getCode(),dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand()));
    }
    public boolean DeleteItem(itemDTO dto)throws SQLException, ClassNotFoundException{
        itemDAOimpl dao = new itemDAOimpl();
        return dao.DeleteItem(new item(dto.getCode()));
    }
}
