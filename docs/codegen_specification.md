This document records the code specification for the Triangle programming language.

## Code Functions

```
  run : Program -> Instruction*

  execute : Command -> Instruction*

  evaluate : Expression -> Instruction*

  elaborate : Declaration -> Instruction*

  fetch : Vname -> Instruction*
  assign : Vname -> Instruction*
  fetch-address : Vname -> Instruction*

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
  execute [[I (AP)]] = 
    pass-argument AP
    CALL(r) e where (l, e) = address of routine bound to I,
                    cl = current routine level (call level)
                    r = display-register(cl, l)

  pass-argument [[E]] = 
    evaluate E

  pass-argument [[V]] = 
    fetch-address V

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

```
  evaluate [[ [E] ]] = 
    visit type(E)
    visit E.AA
```

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


  * KnownValue - a value bound in a constant declaration, whose RHS is a literal.

  * UnknownValue - a value bound in a const declaration, whose RHS contains entities evaluated at runtime. Also an argument value bound to a constant parameter.

  * KnownAddress - an address allocated in a variable declaration. Each such address is represented by a (level, displacement) pair.

  * UnknownAddress - an argument address bound to a variable parameter.

  * KnownRoutine - a routine (user-defined) bound in a procedure or function declaration.

  * UnknownRoutine - describes a closure argument bound to a procedure or function parameter.

  * PrimitiveRoutine - a primitive routine defined by the TAM.

  * EqualityRoutine - a special primitive routine that checks for equality of (same type) values of any type.
  
  * Field - describes a field of a record.

  * Type representation - describes a type. Every type has a known constant size.


#### SimpleVname

##### KnownValue

```
  fetch [[I]] =
    LOADL v where v = value bound to I
```

##### UnknownValue or KnownAddress

```
  fetch [[I]] =
    if I is indexed
      LOADA d[r] where d = address of entity + offset(I)
      CALL add
      LOADI(s)
    else
      LOAD(s) d[r] where s = size(type(I))
                      (l, d) = address bound to I
                      cl = current routine level
                      r = display-register(cl, l)
```

```
  assign [[I]] = (only for KnownAddress)
    if I is indexed 
      ! the index value is already available on the stack top
      LOADA d[r] where d = address of entity + offset of I (array base address)
      CALL add ! effective address of index item
      STOREI(s)
    else
      STORE(s) d[r]
```

```
  fetch-Address [[I]] = (only for KnownAddress)
    LOADA d[r]
    if I is indexed
      CALL add
```

##### UnknownAddress

```
  fetch [[I]] =
    LOAD(addrSize) d[r]
    if I is indexed
      CALL add
    if offset(I) != 0
      LOADL offset(I)
      CALL add
    LOADI(s) 
```

```
  assign [[I]] = 
    LOAD(addrSize) d[r] where addrSize is the addressSize (1 in TAM)
    ! the index value is, again, already on the stack top
    if I is indexed
      CALL add
    if offset(I) > 0 
      LOADL offset(I)
      CALL add
    STOREI(S) 
```

```
  fetch-address [[I]] =
    LOAD(addrSize) d[r]
    if I is indexed
      CALL add
    if offset(I) != 0
      LOADL offset(I)
      CALL add
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

```
  elaborate [[proc I (FPS) ~ C]] =
    JUMP g
 e: execute C
    RETURN(0) d, where d = size of FPS
  g:  
```

#### FuncDeclaration

```
  elaborate [[func I(FP): T ~ C]] =
    JUMP g
  e: execute C
    RETURN(n) d, where d = size of FPS, n = size of T
  g:
```

#### SequentialDeclaration

```
  elaborate [[D1; D2]] = 
    elaborate D1
    elaborate D2
```
