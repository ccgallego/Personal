import { Component, OnInit, Input } from '@angular/core';
import { Usuarios } from '../model/Usuarios';
import { FormularioService } from './formulario.service';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html',
  styleUrls: ['./formulario.component.css'],
  providers:[FormularioService]
})
export class FormularioComponent implements OnInit {

  public form: FormGroup;


  datos: Usuarios;
  user: Usuarios = new Usuarios();
 

  constructor(private formularioService: FormularioService, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.form = this.formBuilder.group({
      nombre: new FormControl('', [Validators.email,Validators.required]),
      cedula: new FormControl('',[Validators.required, Validators.maxLength(10)]),
      correo: new FormControl('', [Validators.required]),
      direccion: new FormControl('', [Validators.required])
    });
  }

  limpiar(){
    this.form.reset();
  }

  enviarDatos(){
    this.formularioService.save(this.form.value).subscribe(res => {
      if(res != null){
        this.datos = res;
        this.limpiar();
      }
    }, error => {
      console.log(error.error.message);
    }
    )
  }

}
