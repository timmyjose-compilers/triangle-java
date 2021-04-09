This document records the code specification for the Triangle programming language.

## Code Functions

```
  run : Program -> Instruction*

  execute : Command -> Instruction*

  evaluate : Expression -> Instruction*

  elaborate : Declaration -> Instruction*

  fetch : Vname -> Instruction*
  assign : Vname -> Instruction*

```

Expected behaviour of code functions:

```
  ---------------------------------------------------------------------------------------------------------------------------------------
  | Phrase class | Code Function | Effect of generated code                                                                             |
  |-------------------------------------------------------------------------------------------------------------------------------------|
  | Program      | run P         | Run the program P and then halt, starting and stopping with an empty stack.                          |
  | Command      | execute C     | Execute the command C, possibly updating variables, but neither expanding nor contracting the stack. |
  | Expression   | evaluate E    | Evaluate the expression E, pushing its result on the stack, but having no other effects.             |
  | V-name       | fetch V       | Push the value of the variable or value V onto the stack.                                            |
  | V-name       | assign V      | Pop a value from the stack top, and store it in the variable V                                       |
  | Declaration  | elaborate D   | Elaborate the declaration D, expanding the stack for any variables or constants declared therein     |
  ---------------------------------------------------------------------------------------------------------------------------------------

```

## Code Templates

### Program

```
  run [[Program]] =
  run [[C]] = 
    execute C
    HALT
```

### Command

#### AssignCommand

```
  execute [[V := E]] =
    evaluate E
    assign V
``

#### CallCommand

```
  execute [[I (E)]] = 
    evaluate E
    CALL P, where p = addr(I)
```

#### SequentialCommand

```
  execute [[C1 ; C2]] = 
    execute C1
    execute C2

```

#### IfCommand

```
  execute [[if E then C1 else C2]] =
    evaluate E
    JUMPIF(0) g
    execute C1
    JUMP h
 g: execute C2
 h:  
    
```

#### WhileCommand

```
  execute [[while E do C]] = 
    JUMP h
 g: execute C
 h: evaluate E
    JUMPIF(1) g
```

#### LetCommand

```
  execute [[let D in C]] =
    elaborate D
    execute C
    POP(0) s, if s > 0, where s is the amount of storage allocated by D

```

### Expression

##### IntegerExpression

```
  evaluate [[IL]] = 
    LOADL v, where v = value(IL)

```

#### VnameExpression

```
  evaluate [[V]] = 
    fetch V
```

#### IfExpression

#### CallExpression

#### LetExpression

#### ArrayExpresssion

#### RecordExpresssion

#### UnaryExpression

```
  evaluate [[O E]] = 
    evaluate E
    CALL p, where p is the address of the primitive routine O
```

#### BinaryExpression

```
  evaluate [[E1 O E2]] = 
    evaluate E1
    evaluate E2
    CALL p, where p is the address of the primitive routine O
```

```
  evaluate [[E + 1]] = 
    evaluate E
    CALL succ
```

### V-name

#### SimpleVname

```
  fetch [[I]] = 
    LOAD(s) d[r]
  
```

```
  assign [[I]] = 
    STORE(s) d[r]
```

#### DotVname


#### SubscriptVname


### Declaration

##### ConstDeclaration

```
  elaborare [[const I ~ E]] = 
    evaluate E
```

```
  fetch [[I]] = 
    LOADL v

  elaborare [[const I ~ IL]] =
    
```

##### VarDeclaration

````
  elaborate [[var I: T]] =
    PUSH s
````

#### TypeDeclaration

#### ArrayDeclaration

#### RecordDeclaration

#### ProcDeclaration

#### FuncDeclaration

#### SequentialDeclaration

```
  elaborate [[D1; D2]] = 
    elaborate D1
    elaborate D2
```
