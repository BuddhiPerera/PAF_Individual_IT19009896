package lk.sliit.PAF.user.dao;


import lk.sliit.PAF.user.dto.BuyerDTO;

import java.util.ArrayList;
import java.util.List;

public class BuyerDAOImpl {
    private static BuyerDAOImpl buyerDAO;
    private static List<BuyerDTO> buyerDTOList = new ArrayList<>();


    public static BuyerDAOImpl getInstance() {
        if (buyerDAO == null) {
            buyerDAO = new BuyerDAOImpl();
        }
        return buyerDAO;
    }

    public int add(BuyerDTO buyerDTO) {
        int newId = buyerDTOList.size() + 1;
        buyerDTO.setId(newId);
        buyerDTOList.add(buyerDTO);

        return newId;
    }

    public BuyerDTO get(int id) {
        BuyerDTO findBuyer = new BuyerDTO(id);
        int index = buyerDTOList.indexOf(findBuyer);
        if (index >= 0) {
            return buyerDTOList.get(index);
        }
        return null;
    }

    public boolean delete(int id) {
        BuyerDTO findBuyer = new BuyerDTO(id);
        int index = buyerDTOList.indexOf(findBuyer);
        if (index >= 0) {
            buyerDTOList.remove(index);
            return true;
        }

        return false;
    }

    public boolean update(BuyerDTO buyerDTO) {
        int index = buyerDTOList.indexOf(buyerDTO);
        if (index >= 0) {
            buyerDTOList.set(index, buyerDTO);
            return true;
        }
        return false;
    }

    public List<BuyerDTO> listAll() {
        return new ArrayList<>(buyerDTOList);
    }
}
