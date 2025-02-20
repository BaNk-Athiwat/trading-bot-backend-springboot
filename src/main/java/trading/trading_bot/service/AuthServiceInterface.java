package trading.trading_bot.service;

import trading.trading_bot.model.UserDetailsModel;
import trading.trading_bot.model.request.SigninRequestModel;
import trading.trading_bot.model.request.SignupRequestModel;
import trading.trading_bot.model.response.JwtResponseModel;

public interface AuthServiceInterface {

    UserDetailsModel signup(SignupRequestModel req);

    JwtResponseModel signin(SigninRequestModel req);

}
