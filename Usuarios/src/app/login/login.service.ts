import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  public login(usuario: string, password: string): Observable<any> {
    return this.http.get<any>(environment.apiUrl +'/usuarios/login/' +  usuario + '/' + password);
  }

}
