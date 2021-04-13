package com.z0ltan.compilers.triangle.encoder;

public class Machine {
  // machine status
  public static int status = Machine.VMStatuses.notStarted;

  // code store
  private static final int CODESIZE = 1024;
  private static final int PRIMITIVES_COUNT = 28;
  public static final int maxRoutineLevels = 7;
  public static Instruction code[] = new Instruction[CODESIZE];
  public static int CB = 0;
  public static int CT = Machine.CB;
  public static int CP = Machine.CT;
  public static int PB = CODESIZE;
  public static int PT = CODESIZE + PRIMITIVES_COUNT;


  // data store
  private static final int DATASIZE = 1024;
  public static int data[] = new int[DATASIZE];
  public static int SB = 0;
  public static int ST = Machine.SB;
  public static int LB = 0;
  public static int HB = DATASIZE;
  public static int HT = Machine.HB;


  // opcodes
  static class Opcodes {
    public static final int LOADOp = 0;
    public static final int LOADAOp = 1;
    public static final int LOADIOp = 2;
    public static final int LOADLOp = 3;
    public static final int STOREOp = 4;
    public static final int STOREIOp = 5;
    public static final int CALLOp = 6;
    public static final int CALLIOp = 7;
    public static final int RETURNOp = 8;
    public static final int PUSHOp = 10;
    public static final int POPOp = 11;
    public static final int JUMPOp = 12;
    public static final int JUMPIOp = 13;
    public static final int JUMPIFOp = 14;
    public static final int HALTOp = 15;

    public static String opcodeName(final int op) {
      switch (op) {
        case LOADOp:
          return "LOAD";

        case LOADAOp:
          return "LOADA";

        case LOADIOp:
          return "LOADI";

        case LOADLOp:
          return "LOADL";

        case STOREOp:
          return "STORE";

        case STOREIOp:
          return "STOREI";

        case CALLOp:
          return "CALL";

        case CALLIOp:
          return "CALLI";

        case RETURNOp:
          return "RETURN";

        case PUSHOp:
          return "PUSH";

        case POPOp:
          return "POP";

        case JUMPOp:
          return "JUMP";

        case JUMPIOp:
          return "JUMP";

        case JUMPIFOp:
          return "JUMPIF";

        case HALTOp:
          return "HALT";

        default:
          throw new IllegalStateException("unknown opcode: " + op);
      }
    }
  }

  // registers

  static class Registers {
    public static final int CBr = 0;
    public static final int CTr = 1;
    public static final int PBr = 2;
    public static final int PTr = 3;
    public static final int SBr = 4;
    public static final int STr = 5;
    public static final int HBr = 6;
    public static final int HTr = 7;
    public static final int LBr = 8;
    public static final int L1r = 9;
    public static final int L2r = 10;
    public static final int L3r = 11;
    public static final int L4r = 12;
    public static final int L5r = 13;
    public static final int L6r = 14;
    public static final int L7r = 15;

    public static String registerName(final int r) {
      switch (r) {
        case CBr:
          return "CB";

        case CTr:
          return "CT";

        case PBr:
          return "PB";

        case PTr:
          return "PT";

        case SBr:
          return "SB";

        case STr:
          return "ST";

        case HBr:
          return "HB";

        case HTr:
          return "HT";

        case LBr:
          return "LB";

        case L1r:
          return "L1";

        case L2r:
          return "L2";

        case L3r:
          return "L3";

        case L4r:
          return "L4";

        case L5r:
          return "L5";

        case L6r:
          return "L6";

        case L7r:
          return "L7";

        default:
          throw new IllegalStateException("invalid register: " + r);

      }
    }
  }

  // primitive routine offsets

  static class Primitives {
    public static final int idDisplacement = 1;
    public static final int notDisplacement = 2;
    public static final int andDisplacement = 3;
    public static final int orDisplacement = 4;
    public static final int succDisplacement = 5;
    public static final int predDisplacement = 6;
    public static final int negDisplacement = 7;
    public static final int addDisplacement = 8;
    public static final int subDisplacement = 9;
    public static final int multDisplacement = 10;
    public static final int divDisplacement = 11;
    public static final int modDisplacement = 12;
    public static final int ltDisplacement = 13;
    public static final int leDisplacement = 14;
    public static final int geDisplacement = 15;
    public static final int gtDisplacement = 16;
    public static final int eqDisplacement = 17;
    public static final int neDisplacement = 18;
    public static final int eolDisplacement = 19;
    public static final int eofDisplacement = 20;
    public static final int getDisplacement = 21;
    public static final int putDisplacement = 22;
    public static final int geteolDisplacement = 23;
    public static final int puteolDisplacement = 24;
    public static final int getintDisplacement = 25;
    public static final int putintDisplacement = 26;
    public static final int newDisplacement = 27;
    public static final int disposeDisplacement = 28;

    public static String primitiveName(int offset) {
      switch (offset) {
        case  Machine.Primitives.idDisplacement:
          return "id";

        case  Machine.Primitives.notDisplacement:
          return "not";

        case  Machine.Primitives.andDisplacement:
          return "and";

        case  Machine.Primitives.orDisplacement:
          return "or";

        case  Machine.Primitives.succDisplacement:
          return "succ";

        case  Machine.Primitives.predDisplacement:
          return "pred";

        case  Machine.Primitives.negDisplacement:
          return "neg";

        case  Machine.Primitives.addDisplacement:
          return "add";

        case  Machine.Primitives.subDisplacement:
          return "sub";

        case  Machine.Primitives.multDisplacement:
          return "mult";

        case  Machine.Primitives.divDisplacement:
          return "div";

        case  Machine.Primitives.modDisplacement:
          return "mod";

        case  Machine.Primitives.ltDisplacement:
          return "lt";

        case  Machine.Primitives.leDisplacement:
          return "le";

        case  Machine.Primitives.geDisplacement:
          return "ge";

        case  Machine.Primitives.gtDisplacement:
          return "gt";

        case  Machine.Primitives.eqDisplacement:
          return "eq";

        case  Machine.Primitives.neDisplacement:
          return "ne";

        case  Machine.Primitives.eolDisplacement:
          return "eol";

        case  Machine.Primitives.eofDisplacement:
          return "eof";

        case  Machine.Primitives.getDisplacement:
          return "get";

        case  Machine.Primitives.putDisplacement:
          return "put";

        case  Machine.Primitives.geteolDisplacement:
          return "geteol";

        case  Machine.Primitives.puteolDisplacement:
          return "puteol";

        case  Machine.Primitives.getintDisplacement:
          return "getint";

        case  Machine.Primitives.putintDisplacement:
          return "putint";

        case  Machine.Primitives.newDisplacement:
          return "new";

        case  Machine.Primitives.disposeDisplacement:
          return "dispose";

        default:
          throw new IllegalStateException("invalid offset for primitive routines: " + offset);
      }
    }
  }

  static class Repr {
    // supported int range
    public static final int minIntRep = -32767;
    public static final int maxIntRep = 32768;

    // boolean representation
    public static final int falseRep = 0;
    public static final int trueRep = 1;
  }

  // sizes of common types (in words)
  static class Sizes {
    public static final int intSize = 1;
    public static final int charSize = 1;
    public static final int boolSize = 1;
    public static final int addressSize = 1;
    public static final int closureSize = 2;
    public static final int linkDataSize = 3;
  }

  // statuses
  static class VMStatuses {
    public static final int notStarted = -1;
    public static final int running = 0;
    public static final int halted = 1;
    public static final int failedDatastoreFull = 2;
    public static final int failedInvalidCodeAddress = 3;
    public static final int failedInvalidInstruction = 4;
    public static final int failedOverflow = 5;
    public static final int failedZeroDivide = 6;
    public static final int failedIOError = 7;
    public static final int failedInvalidDatum = 8;
  }
}

