#!/bin/bash

error=/tmp/error.log

function create_user {
    # Comprobar validez del shell
    if [ ! -f $shell ]; then
        echo "$0: shell de \"$user\" no es un fichero" >> $error
        return 1
    fi

    # intenta crear el usuario
    if useradd -c "$full_name" -d "$home" -m -s "$shell" "$user" &> /dev/null; then
        return 0
    else
        echo "$0: nombre de usuario de \"$user\" no valido" >> $error
        return 1
    fi
}

function create_group {
    if addgroup "$group" &> /dev/null; then
        return 0
    else
        echo "$0: ha habido un error creando el grupo \"$group\"" >> $error
        return 1
    fi
}

function activate_user {
    echo -e "$password\n$password\n" | passwd $user &> /dev/null
}

function user_dont_exist {
    if id "$1" &> /dev/null; then
        return 1    # usuario existe
    else 
        return 0    # usuario no existe
    fi
}

function group_dont_exist {
    if grep $group /etc/group &> /dev/null; then
        return 1    # grupo existe
    else
        return 0    # grupo no existe
    fi
}

# Comprueba permisos de superusuario
if ! [ $(id -u) = 0 ]; then
    echo "$0: No tienes los permisos suficientes" >> $error
    exit 1
fi

# Comprueba que el numero de argumentos sea el correcto
if [ "$#" -ne 1 ]; then
    echo "$0: Numero de argumentos invalido" >> $error
    exit 1
fi

file=$1
while IFS=';' read -r user password full_name shell home group; do 

    group=$(echo $group | tr -d '\r')

    if  user_dont_exist $user; then
        if create_user "$user" "$password" "$full_name" "$shell" "$home"; then
            activate_user "$user" "$password"
        else
            continue
        fi
    fi

    if group_dont_exist "$group"; then
        if ! create_group "$group"; then
            userdel -r $user &> /dev/null
            continue
        fi
    fi

    usermod -a -G "$group" "$user"

done < $file

# Comprueba si ha habido errores durante la ejecucion del archivo
if [ -f $error ]; then
    n_errors=$(wc -l $error | awk '{print $1}')
    rm $error
    echo "Se han producido $n_errors errores durante la ejecucion del script"
    exit 1
else
    echo "Script ejecutado correctamente"
    exit 0
fi