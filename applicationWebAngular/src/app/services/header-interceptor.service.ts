
import {Injectable} from "@angular/core";
import {HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {AuthService} from "./auth.service";

@Injectable()
export class HeaderInterceptorService implements HttpInterceptor {

  constructor(private authService : AuthService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    // Get the auth token from localStorage
    let bearer = 'Bearer ';
    let authToken = localStorage.getItem('currentUserToken');
    let cleanToken = authToken.replace(/"/g, '');
    let bearerAuthToken = bearer.concat(cleanToken) ;
    console.log('local token:' + cleanToken);


    console.log("front auth: " + this.authService.currentUserValue);


    if (authToken && this.authService.currentUserValue) {
      const authReq = req.clone({
        // headers: req.headers.set('Authorization', 'Bearer  eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJicnVjZS5sZWVAZ21haWwuY29tIiwiZXhwIjoxNTc2Nzg4MzE3LCJpYXQiOjE1NzY3NzAzMTd9.pnQrklUb8Usw2TXQEeAc1nLzNMUtrub2SJ59nJu8H9XOflbvg1A2AWD3_ZHoqKeKmVXsVuLB-Ngrm3TQEOVL-Q')
        headers: req.headers.set('Authorization', bearerAuthToken)
        // headers: req.headers.set('Authorization', 'test')

      });
      return next.handle(authReq);
    }

    // const authReq = req.clone({
    //   headers: req.headers.set('Authorization', 'test')
    // });
    // return next.handle(authReq);

    // send http without auth header
    console.log('passage interceptor without auth header');
    return next.handle(req);

    // send cloned request with header to the next handler.
    // return next.handle(authReq);
  }
}
