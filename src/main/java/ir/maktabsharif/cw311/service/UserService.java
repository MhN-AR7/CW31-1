package ir.maktabsharif.cw311.service;

import ir.maktabsharif.cw311.dto.UserLoginDTO;
import ir.maktabsharif.cw311.exception.AuthException;
import ir.maktabsharif.cw311.exception.BusinessException;
import ir.maktabsharif.cw311.dto.UserReqDTO;
import ir.maktabsharif.cw311.dto.UserRespDTO;

import java.util.List;

public interface UserService {
    UserRespDTO register(UserReqDTO userReqDTO) throws BusinessException;
    UserRespDTO findById(Long id) throws BusinessException;
    List<UserRespDTO> findAll();
    UserRespDTO login(UserLoginDTO userLoginDTO) throws AuthException;
}
