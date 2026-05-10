import os
import re

root_dir = r'c:\Users\pc\AndroidStudioProjects\ApplicationEx\app\src\main\java\com\example\applicationex\lab10\shoesstoreapp'
old_pkg = 'com.example.shoesstoreapp'
new_pkg = 'com.example.applicationex.lab10.shoesstoreapp'

# Icon mapping for common missing icons in core library
icon_ref_map = {
    'Icons.Default.ChevronRight': 'Icons.Default.KeyboardArrowRight',
    'Icons.Default.Visibility': 'Icons.Default.Info',
    'Icons.Default.VisibilityOff': 'Icons.Default.Lock',
    'Icons.Default.Remove': 'Icons.Default.Clear',
    'Icons.Default.Inventory2': 'Icons.Default.List',
    'Icons.Default.AttachMoney': 'Icons.Default.ShoppingCart',
    'Icons.Outlined.AttachMoney': 'Icons.Default.ShoppingCart',
    'Icons.Outlined.Inventory': 'Icons.Default.List',
    'Icons.Outlined.Inventory2': 'Icons.Default.List',
    'Icons.Outlined.Assessment': 'Icons.Default.Info',
    'Icons.Filled.Deblur': 'Icons.Default.Refresh',
    'Icons.Filled.Visibility': 'Icons.Default.Info',
    'Icons.Filled.VisibilityOff': 'Icons.Default.Lock',
}

# Mapping for missing drawable resources to Material Icons
drawable_to_icon_map = {
    'R.drawable.ic_favorite': 'Icons.Default.Star',
    'R.drawable.ic_history': 'Icons.Default.Refresh',
    'R.drawable.ic_orders': 'Icons.Default.Info',
    'R.drawable.ic_search': 'Icons.Default.Search',
    'R.drawable.ic_home': 'Icons.Default.Home',
    'R.drawable.ic_discover': 'Icons.Default.Search',
    'R.drawable.ic_cart': 'Icons.Default.ShoppingCart',
    'R.drawable.management': 'Icons.Default.Build',
    'R.drawable.ic_profile': 'Icons.Default.Person',
    'R.drawable.rung': 'Icons.Default.Lock', # Signup/Login logo fallback
}

for root, dirs, files in os.walk(root_dir):
    for file in files:
        if file.endswith('.kt'):
            file_path = os.path.join(root, file)
            try:
                with open(file_path, 'r', encoding='utf-8') as f:
                    content = f.read()
                
                new_content = content
                
                # 1. Fix package imports
                new_content = new_content.replace(old_pkg, new_pkg)
                
                # 2. Fix R imports
                new_content = new_content.replace(f'import {new_pkg}.R', 'import com.example.applicationex.R')
                
                # 3. Remove specific failing imports
                failing_imports = [
                    'import androidx.compose.material.icons.filled.Visibility',
                    'import androidx.compose.material.icons.filled.VisibilityOff',
                    'import androidx.compose.material.icons.filled.ChevronRight',
                    'import androidx.compose.material.icons.filled.Remove',
                    'import androidx.compose.material.icons.filled.BrokenImage',
                    'import androidx.compose.material.icons.filled.Inventory2',
                    'import android.R.attr.order',
                ]
                for imp in failing_imports:
                    new_content = new_content.replace(imp + '\n', '')
                    new_content = new_content.replace(imp, '')

                # 4. Fix icon usage
                for old_ref, new_ref in icon_ref_map.items():
                    new_content = new_content.replace(old_ref, new_ref)
                
                # 5. Fix R.drawable to Icons (if it's being used as ImageVector)
                # If CategoryChip is found, update its definition
                if 'CategoryChip(' in new_content:
                    new_content = new_content.replace('icon: Int', 'icon: androidx.compose.ui.graphics.vector.ImageVector')
                    new_content = new_content.replace('painter = painterResource(id = icon)', 'imageVector = icon')
                
                for draw_res, icon_res in drawable_to_icon_map.items():
                    if draw_res in new_content:
                        # Check if it's used in an Icon call
                        # icon = R.drawable.xxx -> icon = Icons.Default.XXX
                        new_content = new_content.replace(draw_res, icon_res)
                        
                        # Add necessary imports if we injected Icons.Default
                        if 'import androidx.compose.material.icons.Icons' not in new_content:
                            new_content = new_content.replace('import ', 'import androidx.compose.material.icons.Icons\nimport ', 1)
                        if 'import androidx.compose.material.icons.filled.*' not in new_content:
                            new_content = new_content.replace('import ', 'import androidx.compose.material.icons.filled.*\nimport ', 1)

                if new_content != content:
                    with open(file_path, 'w', encoding='utf-8') as f:
                        f.write(new_content)
                    print(f"Updated: {file_path}")
            except Exception as e:
                print(f"Error updating {file_path}: {e}")
