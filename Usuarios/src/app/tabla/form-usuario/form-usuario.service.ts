import { Injectable } from '@angular/core';
import { Usuarios } from 'src/app/model/Usuarios';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FormUsuarioService {

  constructor(private http: HttpClient) { }

  public guardarUsuario(usuario:Usuarios): Observable<any> {
    return this.http.post<any>(environment.apiUrl + '/usuarios',usuario);
  }

}
