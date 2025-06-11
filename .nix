{ pkgs ? import <nixpkgs> {} }:

pkgs.mkShell {
  buildInputs = [ pkgs.maven pkgs.jdk21 ];
  JAVA_HOME = "${pkgs.jdk21.home}";
}
