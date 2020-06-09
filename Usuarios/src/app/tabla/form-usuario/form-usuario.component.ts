import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Usuarios } from 'src/app/model/Usuarios';
import { FormUsuarioService } from './form-usuario.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-form-usuario',
  templateUrl: './form-usuario.component.html',
  styleUrls: ['./form-usuario.component.css']
})
export class FormUsuarioComponent implements OnInit {

  public usuario: Usuarios = new Usuarios()

  public confirmarClave: string

  constructor(public dialogRef: MatDialogRef<FormUsuarioComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,public formService: FormUsuarioService) { }

  ngOnInit() {
    if (this.data.usuario != null) {
      this.usuario = this.data.usuario
    }
  }

  onNoClick(): void {
    this.dialogRef.close();
  }


  enviarUsuario(usuario:Usuarios): void {
    this.dialogRef.close(usuario);
  }

  public guardarUsuario():void {
    this.usuario.estado = true;
    this.formService.guardarUsuario(this.usuario).subscribe(res => {
      if (res != null) {
        Swal.fire(
          'Exito',
          'Usuario creado correctamente',
          'success'
        )
        this.enviarUsuario(res)
      }
    })
  }
}
