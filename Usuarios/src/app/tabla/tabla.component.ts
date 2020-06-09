import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { Usuarios } from '../model/Usuarios';
import { TablaService } from './tabla.service';
import { ConfirmationService, MessageService } from 'primeng/api';
import { trigger,state,style,transition,animate } from '@angular/animations';
import {MatSnackBar} from '@angular/material/snack-bar';
import { FormUsuarioComponent } from './form-usuario/form-usuario.component';
import {MatDialog } from '@angular/material/dialog';
import Swal from 'sweetalert2';



@Component({
  selector: 'app-tabla',
  templateUrl: './tabla.component.html',
  styleUrls: ['./tabla.component.css'],
  providers: [TablaService, ConfirmationService, MessageService],
  animations: [
    trigger('rowExpansionTrigger', [
        state('void', style({
            transform: 'translateX(-10%)',
            opacity: 0
        })),
        state('active', style({
            transform: 'translateX(0)',
            opacity: 1
        })),
        transition('* <=> *', animate('400ms cubic-bezier(0.86, 0, 0.07, 1)'))
    ])
]
})
export class TablaComponent implements OnInit, OnChanges {

  @Input() public datosRecibidos: Usuarios;

  public listUser: Array<Usuarios> = [];

  mostrarTabla: boolean = true;
  typePassword: string = 'hidetextTrue';


  constructor(public dialog: MatDialog,private _snackBar: MatSnackBar, private tablaService: TablaService, private confirmationService: ConfirmationService, private messageService: MessageService) { }

  ngOnInit() {
    this.getUsers();
  }

  ngOnChanges() {
    this.mostrar();
  }

  cambiarEstado(usuario:Usuarios): void {
    if (usuario.estado) {
      usuario.estado= false;
    }else {
      usuario.estado = true
    }
    this.tablaService.editarUsuario(usuario).subscribe(res => {
      console.log(res)
      this.mensaje('Estado cambiado correctamente','Exito')
    })
  }

  mensaje(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 2000,
    });
  }

  public getUsers(): void {
    this.tablaService.getUser().subscribe(res => {
      if(res != null){
        this.listUser = res
      }
    })
  }

  dialogUsuario(): void {
    const dialogRef = this.dialog.open(FormUsuarioComponent, {
      width: '500px',
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result != null) {
        this.listUser.push(result)
      let _data = this.listUser
      this.listUser = null
      this.listUser = _data
      
      }
    });
  }


  mostrar() {
    if(this.datosRecibidos != undefined) {
      this.listUser.push(this.datosRecibidos);
      this.messageService.add({severity:'success', summary: 'Éxito', detail:'Usuario agregado correctamente'});
      this.mostrarTabla = true;
    }
  }

  eliminar(usuario: Usuarios){
    Swal.fire({
      title: '¿Estás seguro?',
      text: "Desea eliminar el usuario " + usuario.usuario ,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, eliminar!'
    }).then((result) => {
      if(result.value){
        this.tablaService.deleteUser(usuario.idUsuario).subscribe(res => {
          if (res != null) {
            Swal.fire(
              'Éxito!',
              'El usuario '+ usuario.usuario + ' se eliminó correctamente',
              'success'
            )
            this.listUser.splice(this.listUser.indexOf(usuario),1);
          }
      }, error => {
        Swal.fire(
          'Éxito!',
          'El usuario '+ usuario.usuario + 'no se pudo eliminar',
          'success'
        )
      })
    }
  })
}

  actualizar(usuario:any): void{
    const dialogRef = this.dialog.open(FormUsuarioComponent, {
      width: '500px',
      data: {usuario: usuario}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });

  }

}
