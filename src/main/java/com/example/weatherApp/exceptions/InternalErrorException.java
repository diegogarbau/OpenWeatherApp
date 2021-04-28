package com.example.weatherApp.exceptions;

 public class InternalErrorException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public InternalErrorException(String mensaje) {
            super(mensaje);
            // TODO Auto-generated constructor stub
        }
}
