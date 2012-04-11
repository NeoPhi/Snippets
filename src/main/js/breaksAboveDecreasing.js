var MAX = 100;

function stage2(breaksAbove, floor, end) {
  if (floor === end) {
    return 0;
  }
  var drops = 0;
  while (true) {
    drops += 1;
    if (floor > breaksAbove) {
      return drops;
    }
    floor += 1;
    if (floor === end) {
      return drops;
    }
  }
}

function stage1(breaksAbove, delta) {
  var floor = delta;
  var drops = 0;
  var lastFloor;
  while (true) {
    lastFloor = floor;
    drops += 1;
    if (floor > breaksAbove) {
      return drops + stage2(breaksAbove, floor - delta + 1, floor);
    } else if (floor === MAX) {
      return drops;
    } else {
      floor += delta;
      if (delta > 1) {
        delta -= 1;
      }
      if (floor > MAX) {
        delta = MAX - lastFloor;
        floor = MAX;
      } else if (floor <= lastFloor) {
        throw {};
      }
    }
  }
}

for (var delta = 1; delta <= MAX; delta += 1) {
  var total = 0;
  var max = 0;
  try {
    for (var breaksAbove = 0; breaksAbove <= MAX; breaksAbove += 1) {
      var drops = stage1(breaksAbove, delta);
      total += drops;
      max = Math.max(max, drops);
    }
  } catch (e) {
    max = 0;
    total = 0;
  }
  console.log([delta, max, total/(MAX + 1)].join('\t'));
}
