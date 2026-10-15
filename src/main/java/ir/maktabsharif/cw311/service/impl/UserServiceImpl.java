package ir.maktabsharif.cw311.service.impl;

import ir.maktabsharif.cw311.command.UserConvertor;
import ir.maktabsharif.cw311.dao.UserDAO;
import ir.maktabsharif.cw311.dto.UserLoginDTO;
import ir.maktabsharif.cw311.exception.AuthException;
import ir.maktabsharif.cw311.exception.BusinessException;
import ir.maktabsharif.cw311.dto.UserReqDTO;
import ir.maktabsharif.cw311.dto.UserRespDTO;
import ir.maktabsharif.cw311.repository.UserRepo;
import ir.maktabsharif.cw311.repository.impl.UserRepoImpl;
import ir.maktabsharif.cw311.service.UserService;
import ir.maktabsharif.cw311.util.PasswordEncoder;
import ir.maktabsharif.cw311.util.Rule;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepo userRepo = new UserRepoImpl();

    @Override
    public UserRespDTO register(UserReqDTO userReqDTO) throws BusinessException {
        Rule.check(
                userReqDTO.getUsername() == null || userReqDTO.getUsername().isBlank(),
                BusinessException::new,
                "Invalid username!"
        );

        Rule.check(
                userRepo.isUserExist(userReqDTO.getUsername()),
                BusinessException::new,
                "Username exists!"
        );

        UserDAO userDAO = UserConvertor.reqDTOToDAO(userReqDTO);

        return UserConvertor.DAOToRespDTO(userRepo.create(userDAO));
    }

    @Override
    public UserRespDTO findById(Long id) throws BusinessException {
        UserDAO userDAO = userRepo.findById(id)
                .orElseThrow(() -> new BusinessException("User not found!"));

        return UserConvertor.DAOToRespDTO(userDAO);
    }

    @Override
    public List<UserRespDTO> findAll() {
        return userRepo.findAll()
                .stream()
                .map(UserConvertor::DAOToRespDTO)
                .toList();
    }

    @Override
    public UserRespDTO login(UserLoginDTO userLoginDTO) throws AuthException {
        UserDAO userDAO = userRepo.findByUsername(userLoginDTO.getUsername())
                .orElseThrow(() -> new AuthException("Login failed!"));

        if (!PasswordEncoder.validatePass(userLoginDTO.getPassword(), userDAO.getPassword())) {
            throw new AuthException("Login failed!");
        }

        return UserConvertor.DAOToRespDTO(userDAO);
    }
}
