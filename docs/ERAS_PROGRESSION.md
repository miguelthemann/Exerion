# 🌌 Exerion: Technological Eras & Progression Guide

In **Exerion**, your civilization advances through **10 technological eras**, starting from primitive mineral extraction to cosmic-level manipulation of energy and space.

Advancing through eras unlocks more powerful machinery, automated processing, energy networks, and high-tier equipment while preventing lower-tier players from interacting with or breaking technology beyond their current civilization level.

---

## 🏛️ How to Progress Through Eras

```mermaid
flowchart LR
    S["Era 0: Stone"] --> C["Era 1: Copper"]
    C --> I["Era 2: Iron"]
    I --> M["Era 3: Mechanical"]
    M --> ST["Era 4: Steel / Steam"]
    ST --> IND["Era 5: Industrial"]
    IND --> D["Era 6: Digital"]
    D --> ADV["Era 7: Advanced"]
    ADV --> Q["Era 8: Quantum"]
    Q --> COSM["Era 9: Cosmic"]
```

1. **Craft or find a Progression Terminal** (`net.exerion.block.ProgressionTerminalBlock`).
2. **Open the Progression Terminal GUI**.
3. **Insert the exact required tribute resources** into the terminal slots.
4. **Click "Advance Era"** (or trigger the evolution payload). A triumphant sound will play, and your new civilization level will be synchronized across the server!

---

## 📋 Comprehensive Era Requirements & Unlocks

| Era | Level | Required Items to Advance | Unlocks & Key Capabilities |
|:---|:---:|:---|:---|
| **0. Stone Age** | `0` | *Default Starting Era* | • Mining **Raw Exerion** underground<br>• Basic stone & wood tools<br>• Crafting the **Exerion Core** |
| **1. Copper Age** | `1` | `64x Raw Copper`<br>`64x Coal`<br>`64x Raw Iron` | • **Copper Gears**<br>• **Water Wheels** (early rotational power)<br>• Foundational metallurgical processing |
| **2. Iron Age** | `2` | `64x Copper Gear`<br>`32x Iron Block`<br>`16x Redstone Dust` | • **Iron Gears** & Gear Blueprints<br>• **Manual Crusher** (ore doubling + 25% extra dust)<br>• **Gear Press** (efficient plate/gear manufacturing) |
| **3. Mechanical Era** | `3` | `64x Iron Gear`<br>`32x Shaft`<br>`16x Water Wheel` | • **Mechanical Shafts** & Transmission lines<br>• **Hand Crank** (manual kinetic torque)<br>• **Mechanical Chute** (automated item transport)<br>• **Wrench** (machine rotation & instant pickup) |
| **4. Steel / Steam Age** | `4` | `64x Steel Ingot`<br>`64x Steam Crystal`<br>`16x Coal Boiler` | • **Coal Boiler** (water + coal $\rightarrow$ high-pressure steam)<br>• **Steam Turbine** (generates kinetic torque + EU electricity)<br>• **Steam Crystals** & **Steam Boots**<br>• **Exerion Casing** and **Exerion Frame** |
| **5. Industrial Era** | `5` | `64x Circuit Board`<br>`64x Exerion Plate`<br>`64x Steel Cable` | • **Electric Generator** (10 EU/t, solid fuel)<br>• **BatBox** (energy storage buffer)<br>• **Electric Crusher** (5x faster than manual crusher)<br>• **Compressor** & **Alloy Smelter**<br>• **Copper & Steel Cables**<br>• **Era Scanner** (block & player era analysis) |
| **6. Digital Era** | `6` | `64x Logic Chip`<br>`64x Exerion Gear`<br>`32x Electric Generator` | • **Logic Chips** & **Circuit Boards**<br>• **Progression Terminal** crafting<br>• **Digital Storage Terminal** (centralized inventory grid)<br>• **Logic Helmet**<br>• **Exerion Cable** |
| **7. Advanced Era** | `7` | `64x Solar Panel`<br>`64x Logic Chip` | • **Copper, Steel & Exerion Solar Panels**<br>• **Geothermal Generator** (lava thermal power)<br>• **Speed & Efficiency Upgrades**<br>• **Pump** (automated fluid extraction) |
| **8. Quantum Era** | `8` | `64x Exerion Core`<br>`1x Plasma Sword` | • **Frequency Ender Chest** (cross-dimensional quantum item transmission)<br>• **Frequency Ender Tank** (cross-dimensional fluid transmission)<br>• **Electric Mining Drill**<br>• **Plasma Sword** |
| **9. Cosmic Era** | `9` | `64x Exerion Core` | • **Mining Laser** (massive localized terrain excavation)<br>• **Exerion Suit Armor** (nadir protection & buffs)<br>• Pinnacle of technological mastery |

---

## 🔒 Era Restriction System

The mod enforces strict progression barriers to maintain balanced survival progression:

1. **Block Breaking:** If a player attempts to break an iron/steel/quantum machine before unlocking its era, the action is cancelled and an alert is displayed:
   `[Exerion] You cannot break this block. Requires <Era>!`
2. **Block Interaction / GUI Opening:** Players cannot access inventories or operation screens of advanced machines until reaching the corresponding era.
3. **Item Use:** Tools, weapons, and scanners are locked to their corresponding technological tiers.

---

## 🛠️ Administrative & Debug Commands

Server operators can inspect or adjust player eras via the `/exerion` command:

```bash
# Check current era of a player
/exerion era get <player>

# Set a player's era directly (stone, copper, iron, mechanical, steel, industrial, digital, advanced, quantum, cosmic)
/exerion era set <player> <era_name>

# Reset a player back to Stone Age
/exerion era set <player> stone
```
