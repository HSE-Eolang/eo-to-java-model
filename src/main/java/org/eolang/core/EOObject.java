package org.eolang.core;

import org.eolang.core.data.EOData;
import org.eolang.core.data.EODataObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;


/**
 * Базовый EO объект. На основе этого класса создаются классы для создания объектов пользователяю
 */
public abstract class EOObject implements Cloneable{
    /**
     * Ссылка на родетеля объекта
     */
    protected EOObject _parent;

    /**
     * Проверка может ли быть датаризован объект при создании для кеширования объекта датаризации.
     * Вариант решения проблемы экспоненциального роста времени датаризации при рекурсии.
     * @return the boolean
     */
    public boolean _isCalculable(){return false;}

    public Optional<EOObject> _getDecoratedObject() {
        return Optional.empty();
    }

    public Optional<EOObject> _getParentObject() {
        return Optional.empty();
    }

    /**
     * Установка родителя объект.
     *
     * @param _parent Объект родитель
     * @return this
     */
    public EOObject _setParent(EOObject _parent){
        if(this._parent == null)
            this._parent = _parent;
        return this;
    }

    /**
     * Функция выполняющая датаризацию объекта
     *
     * @return Данные
     */
    public EOData _getData(){
        Optional<EOObject> decoratee = _getDecoratedObject();
        if(!decoratee.isPresent()) {
            throw new RuntimeException("Object cannot be dataized.");
        }
        return decoratee.get()._getData();
    }

    /**
     * Создание копии объекта.
     *
     * @return копия объекта
     */
    public EOObject _clone() {
        /*try{
            EOObject res  = (EOObject)this.clone();
            for (Field field : this.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                EOObject value = (EOObject)field.get(this);
                //field.set(this, value._clone());
                field.set(this, value);
            }
            return res;
        }catch (CloneNotSupportedException cnsException){
            cnsException.printStackTrace();
        }catch (IllegalAccessException iaException){
            iaException.printStackTrace();
        }
        return new EODataObject();*/
        return this;

    }

    /**
     * Присвоение атрибутам объекта значения null для последующего удаления атрибутов сборщиком мусора.
     */
    /*public void _freeAttributes(){
        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                field.set(this, null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }*/

    /**
     * Копирование атрибута объекта по имени.
     *
     * @param name Имя объекта
     * @return Атрибута объекта
     */
    public EOObject _getAttribute(String name) {
        EOObject res = new EODataObject();
        try{
            for (Field field : this.getClass().getDeclaredFields()) {
                if (field.getName().equals(name)) {
                    field.setAccessible(true);
                    EOObject value = (EOObject) field.get(this);
                    return value._clone();
                }

            }
        }catch (IllegalAccessException iaException){}
        try{
            Class<?> attClasss = Class.forName(this.getClass().getName()+"$EO"+name);
            Constructor<?> attConstructor = attClasss.getConstructor();
            return ((EOObject)attConstructor.newInstance())._setParent(this);
        } catch (Exception e) {}
        if(_parent != null){
            res = _parent._getAttribute(name);
        }
        try{
            if(((EODataObject) res).isNoData()){
                Class<?> attClasss = Class.forName(this.getClass().getPackage().getName() +".EO"+name);
                Constructor<?> attConstructor = attClasss.getConstructor();
                return (EOObject)attConstructor.newInstance();
            }

        } catch (Exception e) {}
        return res;
    }

    /**
     * Копирование атрибута объекта по имени с установкойй свободных аттрибутов
     *
     * @param name    Имя объекта
     * @param freeAtt Свободные аттрибуты
     * @return Атрибута объекта
     */
    public EOObject _getAttribute(String name, EOObject... freeAtt) {
        EOObject res = new EODataObject();
        try {
            String className = this.getClass().getName()+"$EO"+name;
            Class<?> attClasss = Class.forName(className);;
            Constructor<?> attConstructor = Arrays.stream(attClasss.getConstructors())
                    .filter(constructor -> constructor.getParameterTypes().length == freeAtt.length)
                    .findFirst().get();
            return (EOObject)attConstructor.newInstance((Object[])freeAtt);
        } catch (Exception e) {}
        if(_parent != null){
            res = _parent._getAttribute(name, (EOObject[])freeAtt);
        }
        try{
            if(((EODataObject) res).isNoData()){
                String className = this.getClass().getPackage().getName() +".EO"+name;
                Class<?> attClasss = Class.forName(className);
                Constructor<?> attConstructor = Arrays.stream(attClasss.getConstructors())
                        .filter(constructor -> constructor.getParameterTypes().length == freeAtt.length)
                        .findFirst().get();
                return (EOObject)attConstructor.newInstance((Object[])freeAtt);
            }

        } catch (Exception e) {}
        return res;
    }
}
