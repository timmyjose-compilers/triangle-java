let
  var x: Integer;
  var y: Integer
in
  begin
    getint(var x);
    getint(var y);
    if x = y
      then putint(1)
      else putint(2)
  end