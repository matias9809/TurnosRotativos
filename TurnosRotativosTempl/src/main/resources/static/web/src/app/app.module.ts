import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AgregarEmpleadosComponent } from './empleados/components/agregar-empleados/agregar-empleados.component';
import { ModificarEmpleadoComponent } from './empleados/components/modificar-empleado/modificar-empleado.component';
import { VerEmpleadoComponent } from './empleados/components/ver-empleado/ver-empleado.component';
import { ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    AppComponent,
    AgregarEmpleadosComponent,
    ModificarEmpleadoComponent,
    VerEmpleadoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
