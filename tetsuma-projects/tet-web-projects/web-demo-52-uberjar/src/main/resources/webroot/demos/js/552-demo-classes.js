
/*
function Animal(name) {
  this.name = name;
  this.speed = 0;
}


function Rabbit(name) {
  Animal.apply(this, arguments);
  //Animal.call(this, name)	//Или так
}


*/

function initClasses() {


  // Класс Animal
  window.Animal = function(name) {
    this.name = name;
    this.speed = 0;
  }



  Animal.prototype.stop = function() {
    this.speed = 0;
    log2(this.name + ' стоит');
  }

  Animal.prototype.run = function(speed) {
    this.speed += speed;
    log2(this.name + ' бежит, скорость ' + this.speed);
  };


  // Класс Rabbit
  window.Rabbit = function(name) {
    Animal.apply(this, arguments);
    //Animal.call(this, name)	//Или так
  }



  //Наследование
  Rabbit.prototype = Object.create(Animal.prototype);

  //Rabbit.prototype.__proto__ = Animal.prototype;	//Аналогичный метод, но не поддерживается в IE
  //Rabbit.prototype = new Animal();		//Неправильный способ - создаётся лишний объект

  Rabbit.prototype.constructor = Rabbit;

  Rabbit.prototype.jump = function() {
    this.speed++;
    log2(this.name + ' прыгает, скорость ' + this.speed);
  }

  //переопределение метода
  Rabbit.prototype.run = function(speed) {
    // вызвать метод родителя, передав ему текущие аргументы
    Animal.prototype.run.apply(this, arguments);
    this.jump();
  };



}

initClasses();
