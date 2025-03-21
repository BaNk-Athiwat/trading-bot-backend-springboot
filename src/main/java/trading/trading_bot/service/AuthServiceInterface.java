package trading.trading_bot.service;

import trading.trading_bot.model.UserModel;
import trading.trading_bot.model.request.SigninRequestModel;
import trading.trading_bot.model.request.SignupRequestModel;
import trading.trading_bot.model.response.JwtResponseModel;

public interface AuthServiceInterface {

    UserModel signup(SignupRequestModel req);

    JwtResponseModel signin(SigninRequestModel req);

}
