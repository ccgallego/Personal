import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuarios } from '../model/Usuarios';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FormularioService {

  constructor(private http: HttpClient) { }

  public save(usuario:Usuarios): Observable<Usuarios> {
    return this.http.post<Usuarios>(environment.apiUrl + "/usuarios", usuario);
  }
}
