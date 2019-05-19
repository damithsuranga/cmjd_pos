package business;

import dao.custom.impl.customerDAOimpl;
import dto.customerDTO;
import entity.customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class customerBO {

    public List<customerDTO> getallCustomer() throws SQLException, ClassNotFoundException {
        customerDAOimpl customerDAO = new customerDAOimpl();
        List<customer> getAllCustomer = customerDAO.getallCustomer();
        List<customerDTO> dtos =new ArrayList();
        for (customer cus :getAllCustomer
                ) {
            customerDTO customerDTO = new customerDTO(cus.getId(),cus.getName(),cus.getAddress(),cus.getSalary());
            dtos.add(customerDTO);
        }
        return dtos;
    }

    public boolean saveCustomer(customerDTO dto) throws SQLException, ClassNotFoundException {
        customerDAOimpl dao = new customerDAOimpl();
        return dao.SaveCustomer(new customer(dto.getId(),dto.getName(),dto.getAddress(),dto.getSalary()));
    }

    public boolean removeCustomer(customerDTO dto) throws SQLException, ClassNotFoundException {
        customerDAOimpl dao = new customerDAOimpl();
        return dao.DeleteCustomer(new customer(dto.getId()));
    }

}
