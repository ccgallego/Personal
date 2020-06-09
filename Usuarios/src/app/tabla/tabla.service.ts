import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Usuarios } from '../model/Usuarios';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TablaService {

  constructor(private http: HttpClient) { }

  public getUser(): Observable<Usuarios[]>{
    return this.http.get<Usuarios[]>(environment.apiUrl + '/usuarios');
  }

  public deleteUser(idUsuario:number): Observable<Usuarios> {
    return this.http.delete<Usuarios>(environment.apiUrl + '/usuarios/' + idUsuario);
  }

  public editarUsuario(usuario:Usuarios): Observable<Usuarios> {
    return this.http.put<Usuarios>(environment.apiUrl +'/usuarios/' + usuario.idUsuario,usuario);
  }
}
