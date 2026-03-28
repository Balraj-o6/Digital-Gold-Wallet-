package com.example.service;

import com.example.dto.UserDTO;
import com.example.dto.UserPortfolioDTO;

public interface IUserService {
    UserDTO getUserById(Integer userId);
    UserDTO getUserByEmail(String email);
    UserPortfolioDTO getUserPortfolio(Integer userId);
}