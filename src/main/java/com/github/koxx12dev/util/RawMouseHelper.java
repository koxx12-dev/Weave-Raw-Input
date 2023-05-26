package com.github.koxx12dev.util;

import com.github.koxx12dev.RawInput;
import net.minecraft.util.MouseHelper;

public class RawMouseHelper extends MouseHelper {

    @Override
    public void mouseXYChange() {
        this.deltaX = RawInput.dx;
        RawInput.dx = 0;
        this.deltaY = -RawInput.dy;
        RawInput.dy = 0;
    }
}
