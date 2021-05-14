package lk.sliit.PAF.user.dao;

import lk.sliit.PAF.user.dto.BuyerDTO;
import lk.sliit.PAF.user.dto.ResearcherDTO;

import java.util.ArrayList;
import java.util.List;

public class ResearcherDAOImpl {
    private static ResearcherDAOImpl researcherDAO;
    private static List<ResearcherDTO> researcherDTOList = new ArrayList<>();

    public static ResearcherDAOImpl getInstance() {
        if (researcherDAO == null) {
            researcherDAO = new ResearcherDAOImpl();
        }
        return researcherDAO;
    }

    public int add(ResearcherDTO researcherDTO) {
        int newId = researcherDTOList.size() + 1;
        researcherDTO.setId(newId);
        researcherDTOList.add(researcherDTO);

        return newId;
    }

    public ResearcherDTO get(int id) {
        ResearcherDTO findResearcher = new ResearcherDTO(id);
        int index = researcherDTOList.indexOf(findResearcher);
        if (index >= 0) {
            return researcherDTOList.get(index);
        }
        return null;
    }

    public boolean delete(int id) {
        ResearcherDTO findResearcher = new ResearcherDTO(id);
        int index = researcherDTOList.indexOf(findResearcher);
        if (index >= 0) {
            researcherDTOList.get(index);
            return true;
        }

        return false;
    }

    public boolean update(ResearcherDTO researcherDTO) {
        int index = researcherDTOList.indexOf(researcherDTO);
        if (index >= 0) {
            researcherDTOList.set(index, researcherDTO);
            return true;
        }
        return false;
    }

    public List<ResearcherDTO> listAll() {
        return new ArrayList<>(researcherDTOList);
    }
}
