package ir.maktabsharif.cw311.command;

import ir.maktabsharif.cw311.dao.UserDAO;
import ir.maktabsharif.cw311.dto.UserReqDTO;
import ir.maktabsharif.cw311.dto.UserRespDTO;
import ir.maktabsharif.cw311.util.PasswordEncoder;

public class UserConvertor {
    public static UserDAO reqDTOToDAO(UserReqDTO userReqDTO) {
        return UserDAO
                .builder()
                .username(userReqDTO.getUsername())
                .password(PasswordEncoder.encodePass(userReqDTO.getPassword()))
                .build();
    }

    public static UserRespDTO DAOToRespDTO(UserDAO userDAO) {
        return UserRespDTO
                .builder()
                .id(userDAO.getId())
                .username(userDAO.getUsername())
                .build();
    }
}
