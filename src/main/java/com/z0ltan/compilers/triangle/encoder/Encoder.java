package com.z0ltan.compilers.triangle.encoder;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.z0ltan.compilers.triangle.checker.StdEnvironment;
import com.z0ltan.compilers.triangle.error.CodegenError;
import com.z0ltan.compilers.triangle.ast.Visitor;
import com.z0ltan.compilers.triangle.ast.Program;
import com.z0ltan.compilers.triangle.ast.EmptyCommand;
import com.z0ltan.compilers.triangle.ast.AssignCommand;
import com.z0ltan.compilers.triangle.ast.CallCommand;
import com.z0ltan.compilers.triangle.ast.IfCommand;
import com.z0ltan.compilers.triangle.ast.WhileCommand;
import com.z0ltan.compilers.triangle.ast.LetCommand;
import com.z0ltan.compilers.triangle.ast.SequentialCommand;
import com.z0ltan.compilers.triangle.ast.EmptyExpression;
import com.z0ltan.compilers.triangle.ast.CallExpression;
import com.z0ltan.compilers.triangle.ast.IfExpression;
import com.z0ltan.compilers.triangle.ast.LetExpression;
import com.z0ltan.compilers.triangle.ast.VnameExpression;
import com.z0ltan.compilers.triangle.ast.IntegerExpression;
import com.z0ltan.compilers.triangle.ast.CharacterExpression;
import com.z0ltan.compilers.triangle.ast.UnaryExpression;
import com.z0ltan.compilers.triangle.ast.ArrayExpression;
import com.z0ltan.compilers.triangle.ast.RecordExpression;
import com.z0ltan.compilers.triangle.ast.BinaryExpression;
import com.z0ltan.compilers.triangle.ast.Declaration;
import com.z0ltan.compilers.triangle.ast.VarDeclaration;
import com.z0ltan.compilers.triangle.ast.ConstDeclaration;
import com.z0ltan.compilers.triangle.ast.ProcDeclaration;
import com.z0ltan.compilers.triangle.ast.FuncDeclaration;
import com.z0ltan.compilers.triangle.ast.UnaryOperatorDeclaration;
import com.z0ltan.compilers.triangle.ast.BinaryOperatorDeclaration;
import com.z0ltan.compilers.triangle.ast.TypeDeclaration;
import com.z0ltan.compilers.triangle.ast.SequentialDeclaration;
import com.z0ltan.compilers.triangle.ast.SimpleTypeDenoter;
import com.z0ltan.compilers.triangle.ast.IntTypeDenoter;
import com.z0ltan.compilers.triangle.ast.CharTypeDenoter;
import com.z0ltan.compilers.triangle.ast.BoolTypeDenoter;
import com.z0ltan.compilers.triangle.ast.ErrorTypeDenoter;
import com.z0ltan.compilers.triangle.ast.AnyTypeDenoter;
import com.z0ltan.compilers.triangle.ast.ArrayTypeDenoter;
import com.z0ltan.compilers.triangle.ast.RecordTypeDenoter;
import com.z0ltan.compilers.triangle.ast.SingleFieldTypeDenoter;
import com.z0ltan.compilers.triangle.ast.MultipleFieldTypeDenoter;
import com.z0ltan.compilers.triangle.ast.VarFormalParameter;
import com.z0ltan.compilers.triangle.ast.ConstFormalParameter;
import com.z0ltan.compilers.triangle.ast.ProcFormalParameter;
import com.z0ltan.compilers.triangle.ast.FuncFormalParameter;
import com.z0ltan.compilers.triangle.ast.EmptyFormalParameterSequence;
import com.z0ltan.compilers.triangle.ast.SingleFormalParameterSequence;
import com.z0ltan.compilers.triangle.ast.MultipleFormalParameterSequence;
import com.z0ltan.compilers.triangle.ast.VarActualParameter;
import com.z0ltan.compilers.triangle.ast.ConstActualParameter;
import com.z0ltan.compilers.triangle.ast.ProcActualParameter;
import com.z0ltan.compilers.triangle.ast.FuncActualParameter;
import com.z0ltan.compilers.triangle.ast.EmptyActualParameterSequence;
import com.z0ltan.compilers.triangle.ast.SingleActualParameterSequence;
import com.z0ltan.compilers.triangle.ast.MultipleActualParameterSequence;
import com.z0ltan.compilers.triangle.ast.Vname;
import com.z0ltan.compilers.triangle.ast.SimpleVname;
import com.z0ltan.compilers.triangle.ast.DotVname;
import com.z0ltan.compilers.triangle.ast.SubscriptVname;
import com.z0ltan.compilers.triangle.ast.SingleRecordAggregate;
import com.z0ltan.compilers.triangle.ast.MultipleRecordAggregate;
import com.z0ltan.compilers.triangle.ast.SingleArrayAggregate;
import com.z0ltan.compilers.triangle.ast.MultipleArrayAggregate;
import com.z0ltan.compilers.triangle.ast.Identifier;
import com.z0ltan.compilers.triangle.ast.IntegerLiteral;
import com.z0ltan.compilers.triangle.ast.CharacterLiteral;
import com.z0ltan.compilers.triangle.ast.Operator;
import com.z0ltan.compilers.triangle.error.ErrorReporter;

public class Encoder implements Visitor {
  private int nextInstrAddr;

  public Encoder() {
    this.nextInstrAddr = Machine.CB;
    elaborateStdEnvironment();
  }

  private void elaborateStdEnvironment() {
    elaborateStdConst(StdEnvironment.falseDecl, Machine.Repr.falseRep);
    elaborateStdConst(StdEnvironment.trueDecl, Machine.Repr.trueRep);
    elaborateStdPrimitiveRoutine(StdEnvironment.idDecl, Machine.Primitives.idDisplacement);
    elaborateStdPrimitiveRoutine(StdEnvironment.notDecl, Machine.Primitives.notDisplacement);
    elaborateStdPrimitiveRoutine(StdEnvironment.andDecl, Machine.Primitives.andDisplacement);
    elaborateStdPrimitiveRoutine(StdEnvironment.orDecl, Machine.Primitives.orDisplacement);
    elaborateStdPrimitiveRoutine(StdEnvironment.succDecl, Machine.Primitives.succDisplacement);
    elaborateStdPrimitiveRoutine(StdEnvironment.predDecl, Machine.Primitives.predDisplacement);
    elaborateStdPrimitiveRoutine(StdEnvironment.negDecl, Machine.Primitives.negDisplacement);
    elaborateStdPrimitiveRoutine(StdEnvironment.addDecl, Machine.Primitives.addDisplacement);
    elaborateStdPrimitiveRoutine(StdEnvironment.subDecl, Machine.Primitives.subDisplacement);
    elaborateStdPrimitiveRoutine(StdEnvironment.multDecl, Machine.Primitives.multDisplacement);
    elaborateStdPrimitiveRoutine(StdEnvironment.divDecl, Machine.Primitives.divDisplacement);
    elaborateStdPrimitiveRoutine(StdEnvironment.modDecl, Machine.Primitives.modDisplacement);
    elaborateStdPrimitiveRoutine(StdEnvironment.ltDecl, Machine.Primitives.ltDisplacement);
    elaborateStdPrimitiveRoutine(StdEnvironment.leDecl, Machine.Primitives.leDisplacement);
    elaborateStdPrimitiveRoutine(StdEnvironment.geDecl, Machine.Primitives.geDisplacement);
    elaborateStdPrimitiveRoutine(StdEnvironment.gtDecl, Machine.Primitives.gtDisplacement);
    elaborateStdEqualityRoutine(StdEnvironment.eqDecl, Machine.Primitives.eqDisplacement);
    elaborateStdEqualityRoutine(StdEnvironment.neDecl, Machine.Primitives.neDisplacement);
  }
  
  private void elaborateStdConst(final Declaration decl, final int value) {
    if (decl instanceof ConstDeclaration) {
      final ConstDeclaration constDecl = (ConstDeclaration)decl;
      int typeSize = ((Integer)constDecl.E.type.accept(this, null)).intValue();
      constDecl.entity = new KnownValue(typeSize, value);
    }
  }

  private void elaborateStdPrimitiveRoutine(final Declaration decl, final int primOffset) {
    decl.entity = new PrimitiveRoutine(Machine.Sizes.closureSize, primOffset);
  }

  private void elaborateStdEqualityRoutine(final Declaration decl, final int eqOffset) {
    decl.entity = new EqualityRoutine(Machine.Sizes.closureSize, eqOffset);
  }

  public void encodeRun(final Program ast) {
    ast.C.accept(this, new Frame(0, 0));
    emit(Machine.Opcodes.HALTOp, 0, 0, 0);
  }

  private void emit(final int op, final int n, final int r, final int d) {
    final Instruction instr = new Instruction(op, r, n, d);
    Machine.code[nextInstrAddr++] = instr;
  }

  public void saveBinary(final String binaryFile) {
    try (final DataOutputStream os = new DataOutputStream(new FileOutputStream(binaryFile))) {
      for (int addr = Machine.CB; addr < this.nextInstrAddr; addr++) {
        Machine.code[addr].writeInstruction(os);
      }
    } catch (IOException ex) {
      throw new CodegenError("Failed to save to binary file " + binaryFile + ": " + ex.getLocalizedMessage());
    }
  }

  private void patch(final int addr, final int d) {
    Machine.code[addr].d = d;
  }

  private int displayRegister(final int callingLevel, final int declLevel) {
    if (declLevel == 0) {
      return Machine.Registers.SBr;
    } else if (callingLevel - declLevel < Machine.maxRoutineLevels) {
      return Machine.Registers.LBr + (callingLevel - declLevel);
    } else {
      throw new CodegenError("Cannot address beyond " + (Machine.maxRoutineLevels - 1) + " levels up");
    }
  }

  private void encodeStore(final Vname vname, final Frame frame, final int valSize) {
    final RuntimeEntity entity = (RuntimeEntity)vname.accept(this, frame);
    
    if (valSize > 255) {
      throw new CodegenError("Can't store values larger than 255 words");
    }

    if (entity instanceof KnownAddress) {
      final EntityAddress address = ((KnownAddress)entity).address;
      if (vname.indexed) {
        emit(Machine.Opcodes.LOADAOp, 0, displayRegister(frame.level, address.level), address.displacement + vname.offset);
        emit(Machine.Opcodes.CALLOp, Machine.Registers.SBr, Machine.Registers.PBr, Machine.Primitives.addDisplacement);
        emit(Machine.Opcodes.STOREIOp, valSize, 0, 0);
      } else {
        emit(Machine.Opcodes.STOREOp, valSize, displayRegister(frame.level, address.level), address.displacement + vname.offset);
      }
    } else if (entity instanceof UnknownAddress) {
      final EntityAddress address = ((UnknownAddress)entity).address;
      emit(Machine.Opcodes.LOADOp, Machine.Sizes.addressSize, displayRegister(frame.level, address.level), address.displacement);

      if (vname.indexed) {
        emit(Machine.Opcodes.CALLOp, Machine.Registers.SBr, Machine.Registers.PBr, Machine.Primitives.addDisplacement);
      }

      if (vname.offset != 0) {
        emit(Machine.Opcodes.LOADLOp, 0,0, vname.offset);
        emit(Machine.Opcodes.CALLOp, Machine.Registers.SBr, Machine.Registers.PBr, Machine.Primitives.addDisplacement);
      }
      emit(Machine.Opcodes.STOREIOp, valSize, 0, 0);
    } else {
      throw new CodegenError("invalid entity for encodeStore: " + entity);
    }
  }

  private void encodefetch(final Vname vname, final Frame frame, final int valSize) {
    final RuntimeEntity entity = (RuntimeEntity)vname.accept(this, frame);

    if (valSize > 255) {
      throw new CodegenError("Can't load values larger than 255 words");
    }

    if (entity instanceof KnownValue) {
      final int value = ((KnownValue)entity).value;
      emit(Machine.Opcodes.LOADLOp, 0, 0, value);
    } else if ((entity instanceof UnknownValue) || (entity instanceof KnownAddress)) {
      final EntityAddress address = entity instanceof UnknownValue ? ((UnknownValue)entity).address : ((KnownAddress)entity).address;

      if (vname.indexed) {
        emit(Machine.Opcodes.LOADAOp, 0, displayRegister(frame.level, address.level), address.displacement + vname.offset);
        emit(Machine.Opcodes.CALLOp, Machine.Registers.SBr, Machine.Registers.PBr, Machine.Primitives.addDisplacement);
        emit(Machine.Opcodes.LOADIOp, valSize, 0, 0);
      } else {
        emit(Machine.Opcodes.LOADOp, valSize, displayRegister(frame.level, address.level), address.displacement + vname.offset);
      }
    } else if (entity instanceof UnknownAddress) {
      final EntityAddress address = ((UnknownAddress)entity).address;
      emit(Machine.Opcodes.LOADOp, Machine.Sizes.addressSize, displayRegister(frame.level, address.level), address.displacement);
      
      if (vname.indexed) {
        emit(Machine.Opcodes.CALLOp, Machine.Registers.SBr, Machine.Registers.PBr, Machine.Primitives.addDisplacement);
      }

      if (vname.offset != 0) {
        emit(Machine.Opcodes.LOADLOp, 0, 0, vname.offset);
        emit(Machine.Opcodes.CALLOp, Machine.Registers.SBr, Machine.Registers.PBr, Machine.Primitives.addDisplacement);
      }
      emit(Machine.Opcodes.LOADIOp, valSize, 0, 0);
    } else {
      throw new CodegenError("invalid entity for encodeFetch: " + entity);
    }
  }

  private void encodeFetchAddress(final Vname vname, final Frame frame) {
    final RuntimeEntity entity = (RuntimeEntity)vname.accept(this, frame);

    if (entity instanceof KnownAddress) {
      final EntityAddress address = ((KnownAddress)entity).address;
      emit(Machine.Opcodes.LOADAOp, 0, displayRegister(frame.level, address.level), address.displacement + vname.offset);

      if (vname.indexed) {
        emit(Machine.Opcodes.CALLOp, Machine.Registers.SBr, Machine.Registers.PBr, Machine.Primitives.addDisplacement);
      }
    } else if (entity instanceof UnknownAddress) {
      final EntityAddress address = ((UnknownAddress)entity).address;
      emit(Machine.Opcodes.LOADOp, Machine.Sizes.addressSize, displayRegister(frame.level, address.level), address.displacement);
      if (vname.indexed) {
        emit(Machine.Opcodes.CALLOp, Machine.Registers.SBr, Machine.Registers.PBr, Machine.Primitives.addDisplacement);
      }

      if (vname.offset != 0) {
        emit(Machine.Opcodes.LOADLOp, 0, 0, vname.offset);
        emit(Machine.Opcodes.CALLOp, Machine.Registers.SBr, Machine.Registers.PBr, Machine.Primitives.addDisplacement);
      }
    } else {
      throw new CodegenError("invalid entity for encodeFetchAddress: " + entity);
    }
  }

  @Override
  public Object visit(final Program prog, final Object arg)  {
    return null;
  }

  @Override
  public Object visit(final EmptyCommand cmd, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final AssignCommand cmd, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final CallCommand cmd, final Object arg) {
    Frame frame = (Frame)arg;
    int sz = ((Integer)cmd.APS.accept(this, frame)).intValue();
    cmd.I.accept(this, new Frame(frame.level, sz));

    return null;
  }

  @Override
  public Object visit(final LetCommand cmd, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final IfCommand cmd, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final WhileCommand cmd, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final SequentialCommand cmd, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final EmptyExpression expr, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final IntegerExpression expr, final Object arg) {
    return Integer.valueOf(0);
  }

  @Override
  public Object visit(final CharacterExpression expr, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final VnameExpression expr, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final LetExpression expr, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final CallExpression expr, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final IfExpression expr, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final UnaryExpression expr, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final BinaryExpression expr, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final ArrayExpression expr, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final RecordExpression expr, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final SingleArrayAggregate agg, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final MultipleArrayAggregate agg, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final SingleRecordAggregate agg, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final MultipleRecordAggregate agg, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final ConstDeclaration decl, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final VarDeclaration decl, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final ProcDeclaration decl, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final FuncDeclaration decl, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final TypeDeclaration decl, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final UnaryOperatorDeclaration decl, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final BinaryOperatorDeclaration decl, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final SequentialDeclaration decl, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final AnyTypeDenoter td, final Object arg) {
    return Integer.valueOf(0);
  }

  @Override
  public Object visit(final ErrorTypeDenoter td, final Object arg) {
    return Integer.valueOf(0);
  }

  @Override
  public Object visit(final BoolTypeDenoter td, final Object arg) {
    if (td.entity == null) {
      td.entity = new TypeRepresentation(Machine.Sizes.boolSize);
    }
    return Integer.valueOf(Machine.Sizes.boolSize);
  }

  @Override
  public Object visit(final CharTypeDenoter td, final Object arg) {
    if (td.entity == null) {
      td.entity = new TypeRepresentation(Machine.Sizes.charSize);
    }
    return Integer.valueOf(Machine.Sizes.charSize);
  }

  @Override
  public Object visit(final IntTypeDenoter td, final Object arg) {
    if (td.entity == null) {
      td.entity = new TypeRepresentation(Machine.Sizes.intSize);
    }
    return Integer.valueOf(Machine.Sizes.intSize);
  }

  @Override
  public Object visit(final ArrayTypeDenoter td, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final SimpleTypeDenoter td, final Object arg) {
    return Integer.valueOf(0);
  }

  @Override
  public Object visit(final SingleFieldTypeDenoter td, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final MultipleFieldTypeDenoter td, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final RecordTypeDenoter td, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final EmptyFormalParameterSequence fps, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final SingleFormalParameterSequence fps, final Object args) {
    return null;
  }

  @Override
  public Object visit(final MultipleFormalParameterSequence fps, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final ConstFormalParameter param, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final VarFormalParameter param, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final ProcFormalParameter param, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final FuncFormalParameter param, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final EmptyActualParameterSequence aps, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final SingleActualParameterSequence aps, final Object arg) {
    return aps.AP.accept(this, arg);
  }

  @Override
  public Object visit(final MultipleActualParameterSequence aps, final Object arg) {
    Frame frame = (Frame)arg;
    int sz1 = ((Integer)aps.AP.accept(this, frame)).intValue();
    Frame frame1 = new Frame(frame, sz1);
    int sz2 = ((Integer)aps.APS.accept(this, frame1)).intValue();
    
    return Integer.valueOf(sz1 + sz2);
  }

  @Override
  public Object visit(final ConstActualParameter param, final Object arg) {
    return param.E.accept(this, arg);
  }

  @Override
  public Object visit(final VarActualParameter param, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final ProcActualParameter param, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final FuncActualParameter param, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final SimpleVname vname, final Object arg) {
    vname.offset = 0;
    vname.indexed = false;

    return vname.I.decl.entity;
  }

  @Override
  public Object visit(final DotVname vname, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final SubscriptVname vname, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final Identifier id, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final IntegerLiteral il, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final CharacterLiteral cl, final Object arg) {
    return null;
  }

  @Override
  public Object visit(final Operator op, final Object arg) {
    return null;
  }
}
