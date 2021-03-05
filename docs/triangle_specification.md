This is the complete specification of the Triangle programming language.

## Entities

  * Value - a truth value, an integer, a character, a record, or an array.

  * Variable - an entity that can hold a value and can be updated. Each variable has a well-defined lifetime.

  * Procedure - an entity whose body may be executed to update variables. A procedure can have constant, variable, procedural, and functional parameters.

  * Function - an entity whose body may be evaluated to yield a value. A function can have constant, variable, procedural, and functional parameters.

  * Type - a type is an entity which describes a set of values. Each value, variable, and function has a specific type.

## Commands

A command is executed in order to update variables. Commands also include side-effects such as I/O.

### Syntax

```
  Command ::= single-Command 
            | Command ; single-Command

  single-Command ::=  epsilon (EmptyCommand)
                  | V-name := Expression (AssignCommand)
                  | Identifier ( Actual-Parameter-Sequence ) (CallCommand)
                  | begin Command end (BracketedCommand)
                  | let Declaration in single-Command (LetCommand)
                  | if Expression then single-Command else single-Command (IfCommand)
                  | while Expression do single-Command (WhileCommand)
```

### Semantics

* The skip (empty) command "" (epsilon) has no effect when executed.

* The assignment command, `V := E` is executed as follows: first E is evaluated to yield a value, the the variable identified by V is updated with this value. V and E must have
  compatible types.

* The procedure calling command `I(APS)` is executed as follows - the actual parameter sequence is evaluated to yield an argument list; then the procedure bound to `I` is called
  with that argument list. `APS` must be compatible with `I`'s formal parameter sequence (`FPS`).

* The sequential command `C1; C2` is executed as follows - first `C1` is executed, and then `C2` is executed.

* The bracketed command `begin C end` is executed as follows - `C` is executed. A bracketed command is used wherever a single-command is expected, but we wish to include possibly
  multiple commands.

* The block command `let D in C` is executed as follows - the declaration `D` is elaborated to produce bindings, and then the command `C` is executed in the environment overlaid 
  by those bindings. These bindings have no effect outside the block command. This is the only command which involves lexical scoping.

* The if command `if E then C1 else C2` is executed as follows - the expression `E` is evaluated first. If true then `C1` is executed else `C2` is executed. `E` must evaluate to a 
  boolean value.

* The while command `while E do C` is executed as follows - the expression `E` is evaluated first. If true then `C` is executed and the command is executed again, else the execution
  is complete. The type of `E` must be boolean.

### Examples

Assume the Standard Environment for the following examples as well as the following declarations:

```
  var i: Integer;
  var s: array 8 of Char;
  var t: array 8 of Char;

  proc sort(var a: array 8 of Char) ~ ...
```

```
  s[i] := '*'; t := s
```

```
  getint(var i);
  putint(i);
  puteol()
```

```
  sort(var s)
```

```
  if s[i] > s[i+1] then
    let var c: Char
    in
      begin
        c := s[i];
        s[i] := s[i+1];
        s[i+1] := c
      end
  else ! skip
```

```
  i := 7;
  while (i > 0) /\ (s[i] = ' ') do
    i := i - 1
```

## Expressions

An expression is evaluated to yield a value. A record aggregate is evaluated to construct a record value from its component values. An array aggregate is evaluated to construct
an array value from its component values.

### Syntax

```
  Expression ::= secondary-Expression
               | let Declaration in Expression (LetExpression)
               | if Expression then Expression else Expression (IfExpression)

  secondary-Expression ::= primary-Expression
                     | primary-Expression Operator secondary-Expression

  primary-Expression ::= Integer-Literal (IntegerExpression)
                      | Character-Literal (CharacterExpression)
                      | V-name (VnameExpression)
                      | Identifier ( Actual-Parameter-Sequence ) (CallExpression)
                      | Operator primary-Expression (UnaryExpression)
                      | ( Expression ) (ParenthesisedExpression)
                      | { Record-Aggregate } (RecordExpression)
                      | [ Array-Aggregate ] (ArrayExpression)

  Record-Aggregate ::= Identifier ~ Expression
                    | Identifier ~ Expression , Record-Aggregate

  Array-Aggregate ::= Expression
                    | Expression , Array-Aggregate
```

### Semantics

  * The expression `IL` yields an integer value. The type of the expression is `Integer`.

  * The expression `CL` yields a character value. The type of the expression is `Char`.

  * The expression `V` yields the value identified by V, or the value of the variable identified by V. The type of the expression is the type of V.

  * The function-calling expression `I(APS)` is evaluated as follows - first the actual parameter sequence `APS` is evaluated to yield an argument list. Then the function
  bound to `I` is called with the argument list. `APS` must be compatible with the function's formal parameter sequence. The type of the expression returned by calling the
  function is the result type of that function.

  * The expression `O E` is equivalent to the function call expression, `O(E)`.

  * The expression `E1 O E2` is equivalent to the function call expression, `O(E1, E2)`.

  * The expression `(E)` yields just the value yielded by `E`.

  * The block expression `let D in E` is evaluated as follows - first the declaration `D` is elaborated, and then `E` is evaluated in the  environment overlaid by the bindings
  produced by the elaboration. The bindings produced have no effect outside the block expression.

  * The if expression `if E1 then E2 else E3` is evaluated as follows - first `E1` is evaluated. If true, then `E2` is evaluated else `E3` is evaluated. `E2` and `E3` must have the
  same type, and `E1` is, of course, boolean.

  * The expression `{RA}` yields just the value yielded by the record-aggregate `RA`. The type of `{I1 ~ E1, I2 ~ E2, ... , IN ~ EN}` is `record I1: T1, I2: T2, ..., IN: TN end`
  where the type of `Ei` is `Ti` and the identifiers are all distinct.

  * The expression `[AA]` yields just the value yielded by the array-aggregate `AA`.  The type of `[E1, E2, ..., EN]` is `array N of T` where the type of each `Ei` is `T`.
  
  * The record aggregate `I ~ E` yields a record value, whose only fields has the identifier `I` and the expression `E`.

  * The record aggregate `I ~ E, RA` yields a record value, whose first field has the identifier `I` and the value yielded by `E`, and whose remaining fields are those of the 
  record value yielded by `RA`.

  * The array aggregate `E` yields an array value, whose only component (with index 0) is the value yielded by `E`.

  * The array-aggregate `E, AA` yields an array value, whose first component (with index 0) is the value yielded by `E`, and whose remaining components are those of the array
  value yielded by `AA`.

### Examples

Assume the Standard Environment and the following declarations:


```
  var current: Char;
  type Date ~ record 
                y: Integer, 
                m: Integer,
                d: Integer
              end;
  var today: Date;

  func multiple(m: Integer, n: Integer): Boolean ~ ...;

  func leap(yr: Integer): Boolean ~ ...;
```

```
  {y ~ today.y + 1, m ~ 1, d ~ 1}

  [31, if leap(today.y) then 29 else 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]

  eof()

  (multiple(yr, 4) /\ \ multiple(yr, 100)) \/ multiple(yr, 400)

  let 
    const shift ~ ord('a') - ord('A');
    
    func capital(ch: Char): Boolean ~
      (ord('A') <= ord(ch)) /\ (ord('ch') <= ord('Z'))
  in
    if capital(current) 
      then chr(ord(current) + shift)
      else current
```

## Value-or-Variable names (Vname)

A value-or-variable-name identifies a value or a variable.

### Syntax

```
  V-name ::= Identifier (SimpleVname)
          | V-name . Identifier (DotVname)
          | V-name [Expression] (SubscriptVname)
```

### Semantics

  * The simple value-or-variable-name `I` identifies a value or a variable with that name. 

  * The qualified value-or-variable-name `V.I` identifies the field `I` of the record value or variable identified by `V`.

  * The indexed value-or-variable-name `V[E]` identifies that component of the array value or variable identified by `V` whose index is identified by the expression `E`.
    `E` must evaluate to an integer. 

### Examples

Assuming the Standard Environment and the following declarations:

```
  type Date ~ record
                m: Integer, d: Integer
              end;

  const xmas ~ { m ~ 12, d ~ 25 };
  var easter: Date;
  var holiday: array 10 of Date

```

```
  easter
```

```
  xmas
```

```
  holiday
```

```
  xmas.m
```

```
  holiday[7]
```

```
  holiday[2].d
```

## Declarations

A declaration is elaborated to produce bindings. The side-effect of elaborating declarations may also be to create and/or update variables.

### Syntax

```
  Declaration ::= single-Declaration
                | Declaration ; single-Declaration

  single-Declaration ::= const Identifier ~ Expression (ConstDeclaration)
                        | var Identifier : Type-Denoter (VarDeclaration)
                        | proc Identifier (Formal-Parameter-Sequence) ~ single-Command (ProcDeclaration)
                        | func Identifier (Formal-Parameter-Sequence): Type-Denoter ~ Expression (FuncDeclaration)
                        | type Identifier ~ Type-Denoter (TypeDeclaration)
```

### Semantics

  * The constant declaration `const I ~ E` is elaborated by binding `I` to the value of the evaluation of the expression `E`.

  * The variable declaration `var I: T` is elaborated by binding `I` to a newly-created variable of type `T`. The value is initially undefined, and this variable only exists
    during the activation of the block that caused this variable to be elaborated.

  * The procedure declaration `proc I(FPS) ~ C` is elaborated by binding `I` to the procedure whose formal-parameter-sequence is `FPS`, and whose body is the command `C`. The
  effect of calling `I` is to associate `FPS` with the argument list, and then executing `C` in the environment overlaid by the bindings produced by `FPS`.

  * The function declaration `func i(FPS): T` is elaborated by binding `I` to the function whose formal-parameter-sequence is `FPS`, and whose body is the expression `E`. The
  effect of calling `I` is to associate `FPS` with the argument list, and then evaluating `E` in the environment overlaid by the bindings produced by `FPS`. The type of the function
  is the type of the expression `E` which must be compatible with `T`.

  * The type declaration `type I ~ T` is elaborated by binding `I` to the type denoted by `T`.

  * The sequential declaration `D1; D2` is elaborated by first elaborating `D1` and then elaborating `D2` in the environment overlaid by the bindings produced by `D1`. The resulting
  bindings are combined to produce the overall bindings for the sequential declaration.

## Examples

Assuming the Standard Environment:

```
  const minichar ~ chr(0)
```

```
  var name: array 20 of Char;
  var initial: Char
```

```
  proc inc(var n: Integer) ~ n := n + 1
```

```
  func odd(n: Integer): Boolean ~
    (n // 2) \= 0
```

```
  func power(a: Integer, n: Integer): Integer ~
    if n = 0 
      then 1
      else a * power(a, n - 1)

```

```
  type Rational ~
    record
      num: Integer,
      den: Integer
    end
```

## Parameters

Formal parameters are use to parameterise a procedure or function with respect to some of the free variables in its body. On calling a procedure, the formal-parameter-sequence is
associated with the actual-parameter-sequence (the argument list), which may be values, variables, procedures, or even functions.

### Syntax

```
  Formal-Parameter-Sequence ::= epsilon
                            | proper-Formal-Parameter-Sequence
  
  proper-Formal-Parameter-Sequence ::= Formal-Parameter
                                    | Formal-Parameter , proper-Formal-Parameter-Sequence

  Formal-Parameter ::= Identifier : Type-Denoter (ConstFormalParameter)
                      | var Identifier : Type-Denoter (VarFormalParameter)
                      | proc Identifier ( Formal-Parameter-Sequence ) (ProcFormalParameter)
                      | func Identifier ( Formal-Parameter-Sequence ) : Type-Denoter (FuncFormalParameter)

  Actual-Parameter-Sequence ::= epsilon (EmptyParameterSequence)
                              | proper-Actual-Parameter-Sequence (MultipleParameterSequence)

  proper-Actual-Parameter-Sequence ::= Actual-Parameter 
                                    | Actual-Parameter , proper-Actual-Parameter-Sequence 

  Actual-Parameter ::= Expression (ConstActualParameter)
                    | var V-name (VarActualParameter)
                    | proc Identifier (ProcActualParameter)
                    | func Identifier (FuncActualParameter)
```

### Semantics

  * A Formal-Parameter-Semantics `FP1, FP2, ..., FPN` is associated with a list of arguments, by associating each `FPi` with the ith argument. The corresponding
  Actual-Parameter-Sequence `AP1, AP2, ... , APN` yields a list of arguments, with each `APi` yielding the ith argument. The number of actual and formal parameters, as well as 
  their types must match up, including the empty case.

  * The formal parameter `I` is associated with an argument by binding `I` to that argument. The corresponding actual parameter must be of the form `E`, and the argument value is
  obtained by evaluating `E`.

  * The formal parameter `var I: T` is associated with an argument variable by binding `I` to that argument. The corresponding actual parameter must be of the form `var V`, and the
  argument value is the one identified by `V`.

  * The formal parameter `proc I(FPS)` is associated with an argument procedure by binding `I` to that argument. The corresponding actual parameter must be of the form `proc I`, 
  and the argument procedure is the one bound to I.

  * The formal parameter `func I(FPS): T` is associated with an argument function by binding `I` to that argument. The corresponding actual parameter must be of the form `func I`,
  and the argument function is the one bound to `I`. 

### Examples

Assuming the Standard Environment:

```
  while \eol() do
    begin
      get(var ch);
      put(ch);
    end
  geteol();
  puteol()
```

```
  proc increment(var count: Integer) ~ 
    count := count + 1

  increment(var freq[n])
```

```
  func uppercase(letter: Char): Char ~
    if (ord('a') <= ord(letter)) /\ (ord(letter) <= ord('z'))
      then chr(ord(letter) - ord('a') + ord('A'))
      else letter

  if uppercase(letter) = 'Q' then quit 
```

```
  type Point ~
    record
      x: Integer,
      y: Integer
    end;
  
  proc shiftright(var pt: Point, xshift: Integer) ~
    pt.x := pt.x + xshift

  shiftright(penposition, 10)
```

```
  proc iteratively(proc p (n: Integer), var a: array 10 of Integer) ~
    let
      var i: Integer
    in
      begin
        i := 0;
        while i < 10 do 
          begin
            p([a[i]]);
            i := i + 1
          end
      end

  var v: array 10 of Integer;

  iteratively(proc putint, var v)
```

## Type Denoters

A type-denoter denotes a data type. Every value, constant, variable, and function has a specified type.

A record-type-denoter denotes the structure of a record type.

### Syntax

```
  Type-Denoter ::= Identifier (SimpleTypeDenoter)      
                | array Integer_Literal of Type-Denoter (ArrayTypeDenoter)
                | record Record-Type-Denoter end (RecordTypeDenoter)

  Record-Type-Denoter ::= Identifier : Type-Denoter
                        | Identifier : Type-Denoter , Record-Type-Denoter
```

### Semantics

  * The type-denoter `I` denotes the type bound to `I`.

  * The type-denoter `array IL of T` denotes a type whose values are arrays. Each array value of this type has an index range whose lower bound is 0 and whose upper bound is one
  less than `IL`. Each array value has a component of type `T` for each value in its index range.

  * The type-denoter `record RT end` denotes a types whose values are records. Each record value of this type has the structure denoted by `RT`.

  * The record-type-denoter `I : T` denotes a record structure whose only field has the identifier `I` and the type `T`.

  * The record-type-denoter `I : T, RT` denotes a record structure whose first field has the identifier `I` and the type `T`, and whose remaining fields are determined by the 
  record structure denoted by `RT`. `I` must not be a field of `RT`.

  Type equivalence in Triangle is structural rather than nominal:

  * Two primitive types are equivalent iff they are of the same type.

  * The type `record ... , Ii: Ti, ... end` is equivalent to `record ..., IIi : TTi, ... end` iff `I,` is the same as `IIi` and `Ti` is equivalent to `TTi`.

  * The type `array n of T` is equivalent to `array n' of T'` iff `n` = `n'` and `T` is equivalent to `T'`.


### Examples

```
  Boolean
```

```
  array 80 of Char
```

```
  record 
    y: Integer,
    m: Month,
    d: Integer
  end
```

```
  record
    size: Integer,
    entry: array 100 of 
            record
              name: array 20 of Char,
              number: Integer
            end
  end
```

## Lexicon

### Syntax

```
  Program ::= (Token | Comment | Blank)*

  Token ::= Integer-Literal | Character-Literal | Identifier | Operator | array | begin | end | const | var | let | in | if | then | else | while | do | proc | record | 
            func | type | . | , | : | ; | ( | ) | ~ | [ | ] | { | } | :=

  Integer-Literal ::= Digit Digit*

  Character-Literal ::= 'Graphic' (Unicode code-point)

  Identifier ::= Letter (Letter | Digit)*

  Operator ::= Op-character Op-character*

  Comment ::= ! Graphic* eol

  Blank ::= space | tab | eol 

  Graphic ::= Letter | Digit | Op-character | space | tab | . | : | ; | , | ~ | ( | ) | [ | ] | { | } | _ | ! | # | $ | " | ` | '

  Letter ::=  a | b | c | ... | z | A | B | C | ... | Z

  Digit ::= 0 | 1 | 2 | ... | 9

  Op-character ::= + | - | * | / | = | < | > | \ | & | @ | % | ^ | ?
```

### Semantics

  * The value of the integer literal dn...d1d9 is dn x 10^n + d(n-1) x 10^(n-1)) + ... + d1 x 10 + d0.

  * The value of the character literal 'c' is the graphic character c.

  * Every character in an identifier is significant. Case is also significant.

  * Every character in an operator is significant. Operators are, in effect, a subclass of identifiers (but they are bound only in the Standard Environment, to unary and binary
  functions).

### Examples

```
  0 1987
```

```
  '%' 'Z' '''
```

```
  x pi v101 Integer get gasFlowRate _foo foo_bar
```

```
  + - <= \/ /\
```

## Programs

A program communicates with the outside world using I/O.

### Syntax

```
  Progam ::= Command
```

### Semantics

  * The program `C` is run by executing `C` in the Standard Environment.

### Examples

## Standard Environment

The Standard Environment contains the following constant, type, procedure, and function declarations:

```
  type Boolean ~ ...;

  const false ~ ...;
  const true ~ ...;

  type Integer ~ ...;
  const maxint ~ ...;

  type Char ~ ...;

  func \(b: Boolean): Boolean ~ ...;
  func /\(b1: Boolean, b2: Boolean): Boolean ~ ...;
  func \/(b1: Boolean, b2: Boolean): Boolean ~ ...;
  func +(i1: Integer, i2: Integer): Integer ~ ...;
  func -(i1: Integer, i2: Integer): Integer ~ ...;
  func *(i1: Integer, i2: Integer): Integer ~ ...;
  func /(i1: Integer, i2: Integer): Integer ~ ...;
  func //(i1: Integer, i2: Integer): Integer ~ ...;
  func <(i1: Integer, i2: Integer): Boolean ~ ...;
  func <=(i1: Integer, i2: Integer): Boolean ~ ...;
  func >(i1: Integer, i2: Integer): Boolean ~ ...;
  func >=(i1: Integer, i2: Integer): Boolean ~ ...;
  func chr(i: Integer): Char ~ ...;
  func ord(c: Char): Integer ~ ...;
  func eof(): Boolean ~ ...;
  func eol(): Boolean ~ ...;
  proc get(var c: Char): Boolean ~ ...;
  proc put(c: Char): Boolean ~ ...;
  proc getint(var i: Integer) ~ ...;
  proc putint(i: Integer) ~ ...;
  proc geteol() ~ ...;
  proc puteol() ~ ...;
```

In addition, for every type `T`, the following functions are available in the Standard Environment:

```
  func =(val1: T, val2: T): Boolean ~ ...;
  func \=(val1: T, val2: T): Boolean ~ ...;
```
