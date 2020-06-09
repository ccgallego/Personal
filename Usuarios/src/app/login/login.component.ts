import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from './login.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [LoginService]
})
export class LoginComponent implements OnInit {

  public usuario: string;
  public password: string;

  constructor(private router: Router,private loginService: LoginService, private _snackBar: MatSnackBar) { 
    this.usuario = '';
    this.password = '';
  }
  
  ngOnInit(){
  }

  validarIngresoDatos():boolean {
    if (this.usuario != null && this.usuario != '') {
      if (this.password != null && this.password != '') {
        return true
      }else {
        this.mensajes('Ingrese clave', 'Info!')
      }
    }else {
      this.mensajes('Ingrese usuario', 'Info!')
    }
    return false;
  }

  public ingresar(): void {
    if (this.validarIngresoDatos()) {
      this.loginService.login(this.usuario,this.password).subscribe(res => {
        if (res != null) {
          console.log(res)
          this.usuario = res;
          sessionStorage.setItem('rol',res.rol);
          sessionStorage.setItem('usuario',res.nombre);
          if (res.rol == 'administrador') {
            this.router.navigate(['/menuPrincipal/usuarios']);
          }
          if (res.rol == 'empleado') {
            this.router.navigate(['/menuPrincipal/']);
          }
        }else {
          this.mensajes('Credenciales incorrectas','Error!');
        }
      })
    } 
  }

  mensajes(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 2000,
    });
  }

}
