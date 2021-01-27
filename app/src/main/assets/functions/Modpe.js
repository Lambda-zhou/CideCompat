const Block = function(){}
const Entity = function(){}
const Item = function(){}
const Level = function(){}
const ModPE = function(){}
const Player = function(){}
const Renderer = function(){}
const Server = function(){}
const ArmorType = function(){}
var newLevell= false
const BlockFace = function(){}
const BlockRenderLayer = function(){}
const ChatColor = function(){}
const DimensionId = function(){}
const Enchantment = function(){}
const EnchantType = function(){}
const EntityRenderType = function(){}
const EntityType = function(){}
const ItemCategory = function(){}
const MobEffect = function(){}
const ParticleType = function(){}
const UseAnimation = function(){}

	Block.defineBlock = function(par1int,par2String,par3Object, par4Object,par5Object,par6Object){ 
	var str = "自定义方块  ：" +
                 "  ID  " +par1int+
                 "  名称  " +par2String+
                 "  材质名称 " +par3Object+
                 "  原材料方块ID  " +par4Object+
                 "  是否透明  " +(par5Object?"透明":"不透明")+
                 "  模型  "+par6Object;
        libs_log("Game.I",str);
	}
	Block.defineLiquidBlock = function(par1int,par2String,par3Object,par4Object){
        var str = "自定义粘液方块  ：" +
                "  ID  " +par1int+
                "  名称  " +par2String+
                "  材质名称  "+par3Object+
                "  原材料  ID"+par4Object;
        libs_log("Game.I",str);
	
	}
	Block.getAllBlockIds = function(){
		return [1,1,1,1,1,1,1];
   }

    Block.getDestroyTime = function(par1int, par2int){return  1;}

    Block.getFriction = function( par1int, par2int){return  10;}

    Block.getRenderType = function( par1int){return  1;}

    Block.getTextureCoords = function( par1int, par2int, par3int){return "未知";}

    Block.setColor = function( par1int, par2Scriptable){
        var str = "设置方块颜色强制渲染  ：" +
                "  ID  " +par1int+
                " 颜色  "+par2Scriptable;
        libs_log("Game.I",str);
       
    }

    Block.setDestroyTime = function( par1int, par2double){
        var str = "设置方块破坏时间  ：" +
                "  ID  " +par1int+
                "  时间  "+par2double;
        libs_log("Game.I",str);
    }

    Block.setExplosionResistance = function( par1int, par2double){
        var str = "设置方块抗爆阻力  ：" +
                "  ID  " +par1int+
                "  能力  "+(par2double==-1.0?"无法炸坏":par2double);
        libs_log("Game.I",str);
       
    }

    Block.setFriction = function( par1int, par2double){
        var str = "设置方块摩擦力  ：" +
                "  ID  " + par1int+
                "  摩擦力 "+par2double;
       libs_log("Game.I",str);
     
    }

    Block.setLightLevel = function( par1int, par2int){
        var str = "设置方块亮度  ：" +
                "  ID  " + par1int+
                "  亮度 "+par2int;
        libs_log("Game.I",str);
       
    }

    Block.setLightOpacity = function( par1int, par2int){
       var str = "设置方块不透明度  ：" +
                "  ID  " + par1int+
                "  不透明度 "+par2int;
        libs_log("Game.I",str);
    }

    Block.setRedstoneConsumer = function( par1int, par2boolean){ 
	var str = "设置方块是否接受红石信号  ：" +
                "  ID  " + par1int+
                "  接受否 "+(par2boolean?"接受":"不接受");
        libs_log("Game.I",str);
       
    }

    Block.setRenderLayer = function( par1int, par2int){
     var tm = null;
        if(par2int==2){
            tm = "全透明";
        }
        if(par2int==1)
            tm = "半透明";
        else{
            tm = "不透明";
        }
        var str = "设置方块属性  ：" +
                "  ID  " + par1int+
                "  属性 "+tm;
        libs_log("Game.I",str);
    }

    Block.setRenderType = function( par1int, par2int){
       var str = "设置方块模型  ：" +
                "  ID  " + par1int+
                "  模型ID "+par2int;
        libs_log("Game.I",str);
    }

    Block.setShape = function( par1int, par2double, par3double, par4double, par5double, par6double, par7double, par8int){
       var str = "设置方块形状  ：" +
                "  ID  " +par1int+
                "  X开始  " +par2double+
                "  Y开始  " +par3double+
                "  Z开始  " +par4double+
                "  X结束  " +par5double+
                "  Y结束  " +par6double+
                "  Z结束  " +par7double+
                "  特殊值  "+par8int;
        libs_log("Game.I",str);
    }
    Block.setShape = function( par1int, par2double, par3double, par4double, par5double, par6double, par7double){
       var str = "设置方块形状  ：" +
                "  ID  " +par1int+
                "  X开始  " +par2double+
                "  Y开始  " +par3double+
                "  Z开始  " +par4double+
                "  X结束  " +par5double+
                "  Y结束  " +par6double+
                "  Z结束  " +par7double+
                "  特殊值  0";
        libs_log("Game.I",str);
    }
    
     print = function(){
     var args = arguments;
     libs_ctx.runOnUiThread(new java.lang.Runnable({run:function(){
     var list = [];
     for(var i = 0;i<args.length;i++){
       list.push(String(args[i]));
     }
     com.xiaohan.seven.cide.api.CideOpenApi.print("toast", list.join(","));
     }}));
     }
 clientMessage = function(str){
 print(str);
}

 libs_log = function(str,str1){
   var args = arguments;
     libs_ctx.runOnUiThread(new java.lang.Runnable({run:function(){
     libs_ctx.sendMessage(str, str1, android.graphics.Color.BLUE);
     }}));
 }
//libs_ctx.setBackgroundColor("#000000")
function loadingfinish(maven){//当JS加载完成后，CJS主动调用该函数
new java.lang.Thread(new java.lang.Runnable(){
run(){
var t=true
try{
while(t){
if(newLevell)
eval("modTick()")
java.lang.Thread.sleep(50);
}
}catch(e){
t=false

libs_ctx.sendMessage("modTick","something error : "+e.toString()+"\n"+e.stack,"#ff0000")
}
}
}).start();
}

const libs_ctx = com.mojang.minecraftpe.MainActivity.currentMainActivity.get()
function modTick(){}
function attackHook(attacker, victim){}

function procCmd(cmd){}
function chatHook(str){}
function continueDestroyBlock(x, y, z, side, progress){}
function destroyBlock(x, y, z, side){}

function projectileHitEntityHook(projectile, targetEntity){}

function eatHook(hearts, saturationRatio){}
function entityAddedHook(entity){}

function entityHurtHook(attacker, victim, halfhearts){}

function entityRemovedHook(entity){}

function explodeHook(entity, x, y, z, power, onFire){}

function serverMessageReceiveHook(str){}

function chatReceiveHook(str, sender){}

function leaveGame(){}
function deathHook(attacker, victim){}

function playerAddExpHook(player, experienceAdded){}

function playerExpLevelChangeHook(player, levelsAdded){}

function redstoneUpdateHook(x, y, z, newCurrent, worldLoading, blockId, blockDamage){}
function screenChangeHook(screenName){}
function selectLevelHook(){}
function newLevel(){}
function startDestroyBlock(x, y, z, side){}

function projectileHitBlockHook(projectile, blockX, blockY, blockZ, side){}


function useItem(x, y, z, itemid, blockid, side, itemDamage, blockDamage){}

 
    Entity.addEffect = function( par1Object,  par2int,  par3int,  par4int,  par5boolean,  par6boolean) {
          var str = "给实体添加药水效果  ：" +
                "  实体  ：" + par1Object +
                "  药水ID ：" + par2int +
                "  持续时间  :" + par3int +
                "  效果等级  :" + par4int +
                "  有否有阴影效果  : " + (par5boolean ? "有" : "无") +
                "  是否产生药水粒子  :" + (par6boolean ? "产生" : "不产生");
        libs_log("Game.I", str);
    }

    Entity.getAll = function() {
		 return [100.0, 101.0, 102.0];
    }

    Entity.getAnimalAge = function( entity) {
		return 100;
    }

    Entity.getArmor = function( entity,  slot) {
        return 100;
    }

    Entity.getArmorCustomName = function( entity,  slot) {
        return "CreateJS";
    }

    Entity.getArmorDamage = function( entity,  slot) {
        return 100;
    }

    Entity.getEntityTypeId = function( entity) {
        if (entity == 100.0) {
            return 11;
        }
        return 18;
    }

    Entity.getExtraData = function( entity,  type) {
        return entity;
    }

    Entity.getHealth = function( entity) {
        return 5;
    }

    Entity.getItemEntityCount = function( entity) {
        return 64;
    }

    Entity.getItemEntityData = function( enetity) {
        return 1;
    }

    Entity.getItemEntityId = function( entity) {
        return 18;
    }

    Entity.getMaxHealth = function( entity) {
        if (entity == 999) return 20;
        return 10;
    }

    Entity.getMobSkin = function( entity) {
        return "CreateJS";
    }

    Entity.getNameTag = function( entity) {
        return "CreateJS";
    }

    Entity.getPitch = function( entity) {
        return 45.0;
    }

    Entity.getYaw = function( entity) {
        return 45.0;
    }

    Entity.getRenderType = function( entity) {
        return 0;
    }

    Entity.getRider = function( entity) {
        return 999;
    }

    Entity.getRiding = function( entity) {
        return 100;
    }

    Entity.getTarget = function( entity) {
        return 999;
    }

    Entity.getUniqueId = function() {
    }

    Entity.getVelX = function( entity) {
        return 1.2;
    }

    Entity.getVelY = function( entity) {
        return 1.2;
    }

    Entity.getVelZ = function( entity) {
        return 1.2;
    }

    Entity.getX = function( entity) {
        return 90.2;
    }

    Entity.getY = function( entity) {
        return 90.2;
    }

    Entity.getZ = function( entity) {
        return 90.2;
    }

    Entity.isSneaking = function( entity) {
        return false;
    }

    Entity.remove = function( entity) {
        libs_log("Game.I", "移除实体  ：" + entity);
    }

    Entity.removeAllEffects = function( entity) {
        libs_log("Game.I", "移除实体所有药水效果  ：" + entity);
    }

    Entity.removeEffect = function( entity,  id) {
        libs_log("Game.I", "移除实体制定药水效果  ：" +
                "  实体  ：" + entity +
                "  效果ID " + id);
    }

    Entity.rideAnimal = function( entity,  raind) {
        var str = "实体骑上实体  ：" +
                "  A目标  ：" + entity +
                "  B目标  ：" + raind;
        libs_log("Game.I", str);
    }

    Entity.setAnimalAge = function( entity,  age) {
       libs_log("Game.I", "设置实体年龄  ：  实体" + entity + "  年龄  ：" + age);
    }

    Entity.setArmor = function( entity,  slot,  id,  ming) {
        var str = "设置实体盔甲  ：" +
                "  实体  " + entity +
                "  格数  " + slot +
                "  ID  " + id +
                "  耐久值  " + ming;
        libs_log("Game.I", str);
    }

    Entity.setArmorCustomName = function( entity,  slot,  name) {
        var str = "设置自定义盔甲名称" +
                "  实体  " + entity +
                "  格子数  " + slot +
                "  名称  " + name;
        libs_log("Game.I", str);
    }

    Entity.setCape = function( entity,  path) {
        var str = "设置实体披风  ：" +
                "  实体  " + entity +
                "  皮肤路径  " + path;
        libs_log("Game.I", str);
    }

    Entity.setCarriedItem = function( entity,  id,  num,  data) {
        var str = "设置实体手持物品  ：" +
                "  实体  ：" + entity +
                "  ID  " + id +
                "  数量  " + num +
                "  特殊值  " + data;
        libs_log("Game.I", str);
    }

    Entity.setCollisionSize = function( entity,  raind,  height) {
        var str = "设置实体碰撞体积  " +
                "  实体  " + entity +
                "  半径  " + raind +
                "  高度  " + height;
        libs_log("Game.I", str);
    }

    Entity.setExtraData = function( entity,  name,  data) {
        var str = "设置实体额外数据" +
                "  数据名称  ：" + name +
                "  数据  " + data;
        libs_log("Game.I", str);
    }

    Entity.setFireTicks = function( entity,  time) {
        var str = "设置实体着火时间  ：" +
                "  实体  " + entity +
                "  着火时间  " + time;
        libs_log("Game.I", str);
    }

    Entity.setHealth = function( entity,  health) {
        var str = "设置实体生命值 ：" +
                "  实体  " + entity +
                "  生命值  " + health;
        libs_log("Game.I", str);
    }

    Entity.setImmobile = function( entity,  gd) {
        var str = "固定实体：" +
                "  实体  " + entity +
                "  固定状态  " + (gd ? "固定" : "释放");
        libs_log("Game.I", str);
    }

    Entity.setMaxHealth = function( obj,  max) {
        var str = "设置实体最大生命值 ：" +
                "  实体  " + obj +
                "  MAX生命值  " + max;
        libs_log("Game.I", str);
    }

    Entity.setMobSkin = function( obj,  path) {
        var str = "设置实体皮肤 ：" +
                "  实体  " + obj +
                "  皮肤路径（材质包下） " + path;
        libs_log("Game.I", str);
    }

    Entity.setNameTag = function( obj,  path) {
        var str = "设置实体铭牌 ：" +
                "  实体  " + obj +
                "  铭牌  " + path;
        libs_log("Game.I", str);
    }

    Entity.setPosition = function( obj,  x,  y,  z) {
        var str = "设置实体坐标 ：" +
                "  实体  " + obj +
                "  X坐标  " + x +
                "  Y坐标  " + y +
                "  Z坐标  " + z;
        libs_log("Game.I", str);
    }

    Entity.setPositionRelative = function( obj,  x,  y,  z) {
        var str = "设置实体与自己的相对位置 ：" +
                "  实体  " + obj +
                "  X偏移  " + x +
                "  Y偏移  " + y +
                "  Z偏移  " + z;
        libs_log("Game.I", str);
    }

    Entity.setRenderType = function( obj,  id) {
        var str = "设置实体模型 ：" +
                "  实体  " + obj +
                "  模型ID  " + id;
        libs_log("Game.I", str);
    }

    Entity.setRot = function( obj,  x,  z) {
        var str = "设置实体视角 ：" +
                "  实体  " + obj +
                "  X视角  " + x +
                "  Z视角  " + z;
        libs_log("Game.I", str);
    }

    Entity.setSneaking = function( obj,  sn) {
        var str = "设置实体潜行状态 ：" +
                "  实体  " + obj +
                "  状态  " + (sn ? "正在潜行" : "正常状态");
        libs_log("Game.I", str);
    }

    Entity.setTarget = function( obj1,  obj2) {
        var str = "设置实体攻击目标 ：" +
                "  实体  " + obj1 +
                "  攻击目标  " + obj2;
        libs_log("Game.I", str);
    }

    Entity.setVelX = function( obj,  sd) {
        var str = "设置实体X偏移速度 ：" +
                "  实体  " + obj +
                "  速度  " + sd;
        libs_log("Game.I", str);
    }

    Entity.setVelY = function( obj,  sd) {
        var str = "设置实体Y偏移速度 ：" +
                "  实体  " + obj +
                "  速度  " + sd;
        libs_log("Game.I", str);
    }

    Entity.setVelZ = function( obj,  sd) {
        var str = "设置实体Z偏移速度 ：" +
                "  实体  " + obj +
                "  速度  " + sd;
        libs_log("Game.I", str);
    }

    Entity.spawnMob = function( x,  y,  z,  id,  path) {
        var str = "生成指定皮肤的实体  " +
                "  X坐标(double)  " + x +
                "  Y坐标(double)  " + y +
                "  Z坐标(double)  " + z +
                "  ID(int)  " + id +
                "  皮肤  " + path;
        libs_log("Game.I", str);
    }
    Entity.setTarget = function(a,b){
      var str = "设置实体攻击目标  " +
                "  攻击者  " + a +
                "  目标  " + b
        libs_log("Game.I", str);
    }
    
     Item.addCraftRecipe = function( id,  num,  data , scriptable){
	 var str = "添加背包合成配方  ：" +
                "  ID  " +id+
                "  数量  " +num+
                "  特殊值  "+data;
        libs_log("Game.I", str);
    }
    Item.addFurnaceRecipe = function( id1, id2, data){
        var str = "添加熔炉配方  ：" +
                "  目标物品ID " +id1+
                "  成品ID " +id2+
                "  成品特殊值  "+data;
        libs_log("Game.I", str);
    }
    Item.addShapedRecipe = function( id,  num,  data , scriptable, scriptable2){
		var str = "添加工作台合成配方  ：" +
                "  ID  " +id+
                "  数量  " +num+
                "  特殊值  "+data;
        libs_log("Game.I", str);
    }
   Item.defineArmor = function( par1int,  par2String,  par3int,  par4String, par5String, par6int, par7int, par8int){
	      var str = "添加新的盔甲  ：" +
                "  盔甲ID  " +par1int+
                "  盔甲材质名字(item.meta中) " +par2String+
                "  盔甲材质偏移值  " +par3int+
                "  盔甲的名字 " +par4String+
                "  盔甲材质（mob文件中）" +par5String+
                "  盔甲防御力  " +par6int+
                "  盔甲耐久度  " +par7int+
                "  盔甲类型  "+par8int;
        libs_log("Game.I", str);
    }

    Item.defineThrowable = function( par1int, par2String, par3int, par4String, par5int){
		 var str = "自定义可抛物品 ：" +
                "  ID  " +par1int+
                "  材质名称  " +par2String+
                "  材质排列值  "+par3int+
                "  物品名称  "+par4String+
                "  最大堆叠  "+par5int;
        libs_log("Game.I", str);
    }

   Item.getCustomThrowableRenderType = function( par1int){ return 0;}

    Item.getMaxDamage = function( par1int){return 100;}

   Item.getMaxStackSize = function( par1int){return 64;}

    Item.getName = function( par1int, par2int, par3boolean){
		if (par3boolean){return "MC";}
        else return "我的世界！";
    }

    Item.getTextureCoords = function( par1int, par2int){return 0;}

    Item.getUseAnimation = function( par1int){ return par1int;}

    Item.internalNameToId = function( par1String){}

    Item.isValidItem = function( par1int){return true;}

    Item.setCategory = function( par1int, par2int, par3int){
		  var str = "设置物品类型 ：" +
                "  ID  " +par1int+
                "  特殊值 " +par2int+
                "  类型  "+par3int;
        libs_log("Game.I", str);
    }
    Item.setEnchantType = function( par1int, par2int, par3int){
		var str = "允许自定义物品附魔类型 ：" +
                "  ID  " +par1int+
                "  附魔类型 " +par2int+
                "  价值  "+par3int;
        libs_log("Game.I", str);
    }

    Item.setHandEquipped = function( par1int, par2boolean){
		var str = "是否为工具 ：" +
                "  ID  " +par1int+
                "  是否为工具形态 " +(par2boolean?"是":"否");
        libs_log("Game.I", str);
    }
    Item.setMaxDamage = function( par1int, par2int, par3int){
		var str = "物品最大耐久值：" +
                "  ID  " +par1int+
                "  特殊值  " +par2int+
                "  耐久值 " +par3int;
        libs_log("Game.I", str);
    }
    Item.setMaxDamage = function( par1int, par3int){
		var str = "物品最大耐久值：" +
                "  ID  " +par1int+
                "  特殊值  " +par3int+
                "  耐久值 0" ;
        libs_log("Game.I", str);
    }
    Item.setProperties = function( par1int, par2Object){
		  var str = "设置物品属性：" +
                "  ID  " +par1int+
                "  属性组  " +"暂时无法解析";
        libs_log("Game.I", str);
    }
Item.setStackedByData = function( par1int, par2boolean){
	var str = "设置物品数据叠加：" +
            "  ID  " +par1int+
            "  未知属性  " +"暂时无法解析";
    libs_log("Game.I", str);
}
    Item. translatedNameToId = function( par1String){return  0;}


    Level.addParticle = function( par1,  par2,  par3,  par4,  par5,  par6,  par7,  par8) {
        libs_log("Game.I", "生成粒子效果  :\n   类型 :" + par1 + "\n" +
                "   X坐标 :" + par2 + "\n" +
                "   Y坐标 :" + par3 + "\n" +
                "   Z坐标 :" + par4 + "\n" +
                "   X速度 :" + par5 + "\n" +
                "   Y速度 :" + par6 + "\n" +
                "   Z速度 :" + par7 + "\n" +
                "   大小 :" + par8);
    }

    Level.biomeIdToName = function( par) {
        return par + "   ：未知，我懒癌晚期不想加";
    }

    Level.canSeeSky = function( x,  y,  z) {
        return true;
    }

    Level.destroyBlock = function( x,  y,  z,  dl) { 
	var str = "破坏方块 :" +
                "  X坐标(int) " + x + "  Y坐标(int) " + y + "  Z坐标(int) " + z + (dl ? "  并且掉落方块" : "但不掉落方块");
        libs_log("Game.I", str);
    }

    Level.dropItem = function( par1,  par2,  par3,  par4,  par5,  par6,  par7) {
		var str = "破坏方块 :" + "  X坐标(double) " + par1 + "  Y坐标(double) " + par2 +
                "  Z坐标(double) " + par3 + "  掉落范围[Y轴(double)] " + par4 + "  物品ID(int) " + par5 + "  物品数量(int) " + par6 + "  物品特殊值(int) " + par7;
        libs_log("Game.I", str);
    }

	Level.executeCommand = function(par1String,par2Boolean){
		libs_inthis.sendMessage("Command",par1String,"#00ffff")
	}
	
    Level.explode = function( x,  y,  z,  radiu,  fire) {
		var str = "产生爆炸  :" +
                "  X坐标(double) " + x +
                "  Y坐标(double) " + y +
                "  Z坐标(double) " + z +
                "  半径(double) " + radiu +
                (fire ? "  并且产生火" : "但不产生火");
        libs_log("Game.I", str);
    }

    Level.getAddress = function() {
        return "??未收入函数表??";
    }

    Level.getBiome = function( x,  y) {
        return 101;
    }

    Level.getBiomeName = function( x,  y) {
        return "未知，暂未收录";
    }

    Level.getBrightness = function(x,  y,  z) {
        return 12;
    }

    Level.getChestSlot = function( x,  y,  z,  num) {
        return 49;
    }

    Level.getChestSlotCount = function( x,  y,  z,  num) {
        return 20;
    }

    Level.getChestSlotCustomName = function( x,  y,  z,  num) {
        return "钻石块";
    }

    Level.getChestSlotData = function( x,  y,  z,  num) {
        return 1;
    }

    Level.getData = function( x,  y,  z) {
        return 1;
    }

    Level.getDifficulty = function() {
        return 3;
    }

    Level.getFurnaceSlot = function( x,  y,  z,  num) {
        return 14;
    }

    Level.getFurnaceSlotCount = function( x,  y,  z,  num) {
        return 64;
    }

    Level.getFurnaceSlotData = function( x,  y,  z,  num) {
        return 1;
    }

    Level.getGameMode = function() {
		return 0;
    }

    Level.getGrassColor = function( x,  z) {
        return 0xffffff;
    }

    Level.getLightningLevel = function() {
        return 0;
    }

    Level.getRainLevel = function() {
        return true;
    }

    Level.getSignText = function( x,  y,  z,  num) {
		return "CreateJS lib for minecraftpe"
    }

    Level.getTile = function( x,  y,  z) {
        return 89;
    }

    Level.getTime = function() {
        return 9999;
    }

    Level.getWorldDir = function() {
        return "MIDE.Dir";
    }

    Level.getWorldName = function() {
        return "MIDE.World";
    }

    Level.playSound = function( x,  y,  z,  name,  vool,  yd) {
		var str = "坐标发出声音  :" +
                "  X坐标(double)  :" + x +
                "  Y坐标(double)  :" + y +
                "  Z坐标(double)  :" + z +
                "  声音名字  :" + name +
                "  声音大小(double)  :" + vool +
                "  音调(double)  :" + yd;
        libs_log("Game.I", str);
    }

    Level.playSoundEnt = function( obj,  name,  vool,  yd) {
		var str = "实体发出声音  :" +
                "  发出声音的实体  :" + obj.toString() +
                "  声音名字  :" + name +
                "  声音大小(double)  :" + vool +
                "  音调(double)  :" + yd;
        libs_log("Game.I", str);
		
    }

    Level.setChestSlot = function( x,  y,  z,  slot,  id,  data,  num) {
		var str = "指定箱子添加物品  ：" +
                "  X坐标(int)  :" + x +
                "  Y坐标(int)  :" + y +
                "  Z坐标(int)  :" + z +
                "  格子(int)  :" + slot +
                "  ID(int)  :" + id +
                "  特殊值(int)  :" + data +
                "  数量(int)  :" + num;
        libs_log("Game.I", str);
    }

    Level.setChestSlotCustomName = function( x,  y,  z,  clot,  name) {
		 var str = "修改箱子指定位置物品名称  ：" +
                "  X坐标(int) :" + x +
                "  Y坐标(int) :" + y +
                "  Z坐标(int) :" + z +
                "  格子(int) :" + clot +
                "  名字(String) :" + name;
        libs_log("Game.I", str);
    }

    Level.setDifficulty = function( difficulty) {
		if (difficulty < 0)
            difficulty = 0;
        if (difficulty > 3)
            difficulty = 3;
        libs_log("Game.I", "难度调整为 " + difficulty);
    }

	Level.setBlockExctraData = function(par1int,par2int,par3int,par4int){
		
	}
	
    Level.setFurnaceSlot = function( x,  y,  z,  slot,  id,  data,  num) {
		var str = "设定熔炉的物品  ：" +
                "  X坐标(int)  " + x +
                "  Y坐标(int)  " + y +
                "  Z坐标(int)  " + z +
                "  格子(int)  " + slot +
                "  ID(int)  " + id +
                "  特殊值(int)  " + data +
                "  数量(int)  " + num;
        libs_log("Game.I", str);
    }

    Level.setGameMode = function( mode) {
		if (mode < 0)
            mode = 0;
        if (mode > 1)
            mode = 1;
        libs_log("Game.I", " 游戏模式变更 " + mode);
    }

    Level.setGrassColor = function( x,  z,  color) {
		var str = "设置草颜色  ：" +
                "  X坐标(int) ：" + x +
                "  Z坐标(int) ：" + z +
                "  颜色码[ARGB(long)] ：0x" + Long.toHexString(color);
        libs_log("Game.I", str);
    }

    Level.setLightningLevel = function( par) {
        libs_log("Game.I", "设置闪电等级  " + par);
    }

    Level.setNightMode = function( par) {
        libs_log("Game.I", "设置夜晚模式  :" + par);
    }

    Level.setRainLevel = function( par) {
        libs_log("Game.I", "设置下雨等级  :" + par);
    }

    Level.setSignText = function( x,  y,  z,  lan,  string) {
		var str = "设置告示牌内容   ：" +
                " X坐标(int)  " + x +
                " Y坐标(int)  " + y +
                " Z坐标(int)  " + z +
                " 行数(int)  " + lan +
                " 内容(String)  " + string;
        libs_log("Game.I", str);
    }

    Level.setSpawn = function( x, y, z){ 
	var str = "设置玩家重生点  :" +
                "  X坐标(int)  "+x+
                "  Y坐标(int)  "+y+
                "  Z坐标(int)  "+z;
        libs_log("Game.I", str);
    }

    Level.setSpawnerEntityType = function( x, y, z, id){
		 var str = "设置刷怪笼实体ID" +
                "  X坐标  "+x+
                "  Y坐标  "+y+
                "  Z坐标  "+z+
                "  实体ID  "+id;
        libs_log("Game.I", str);
    }

    Level.setTile = function( x, y, z, id, data){
		var str = "放置方块  :" +
                "  X坐标  "+x+
                "  Y坐标  "+y+
                "  Z坐标  "+z+
                "  ID  "+id+
                "  特殊值  "+data;
        libs_log("Game.I", str);
    }

    Level.setTime = function( time){
        libs_log("Game.I", "设置当前世界时间  :" + time);
    }

    Level.spawnChicken = function( x, y, z, name){
        return 1000;
    }

    Level.spawnCow = function( x, y, z, name){
        return 1001;
    }

    Level.spawnMob = function( x, y, z, id){
        return 1002;
    }

    Level.spawnMob = function( x, y, z, id, name){
        return 1002;
    }

ModPE.dumpVtable = function(par1String, par2int) {}
ModPE.getBytesFromTexturePack = function(path) {
	var fi = this.openInputStreamFromTexturePack(path);
	var buf = java.lang.reflect.Array.newInstance(java.lang.Byte.TYPE, fi.getChannel().size());
	fi.read(buf);
	fi.close();
	return buf;
}
ModPE.getI18n = function(par1String) {
	return "CreateJS";
}
ModPE.getLanguage = function() {
	return "zh-cn";
}
ModPE.getMinecraftVersion = (function self() {
	var ver;
	try {
		ver = String(libs_inthis.getPackageManager().getPackageInfo("com.mojang.minecraftpe", 0).versionName);
	} catch (e) {
		ver = "1.1.5.0";
	}
	return function() {
		return ver;
	}
})();
ModPE.getOS = function(){
	return "Android";
}
ModPE.langEdit = function(par1String, par2String) {
}

ModPE.leaveGame = function() {
newLevell = false
}
ModPE.log = function(par1String) {
	libs_inthis.sendMessage("Log",par1String,"#ff0000")
}
ModPE.FoundFile = function(path) {}
ModPE.readArray = function(path, par1boolean) {}
ModPE.openInputStreamFromTexturePack = function(path) {
	var ff = new java.io.File("/sdcard/.CreateJS/cache/", path);
	return new java.io.FileInputStream(ff);
}
ModPE.overrideTexture = function(par1String, par2String) {}
ModPE.readData = function(par1String) {
var str = ""
 try{
 return com.lingsatuo.utils.Utils.readDateFromApp(libs_inthis,par1String)
 }catch(e){
 libs_inthis.sendMessage("CreateJSIOError",e.toString()+"\n"+e.stack,-65536)
 return str
 }
}
ModPE.removeData = function(par1String) {
try{
 new java.io.File(libs_inthis.getFilesDir().toString()+"/"+name).delete()
 }catch(e){}
}
ModPE.resetFov = function() {}
ModPE.resetImages = function() {}
ModPE.saveData = function(par1String, par2String) {var str = ""
 try{
 com.lingsatuo.utils.Utils.saveDateToApp(libs_inthis,par1String,par2String)
 }catch(e){
 libs_inthis.sendMessage("CreateJSIOError",e.toString()+"\n"+e.stack,-65536)
 }
}
ModPE.selectLevel = function(par1String) {}
ModPE.setCamera = function(par1Object) {}
ModPE.setFoodItem = function(par1int, par2String, par3int, par4int, par5String, par6int) {}
ModPE.setFov = function(par1double) {}
ModPE.setGameSpeed = function(par1double) {}
ModPE.setGuiBlocks = function(par1String) {}
ModPE.setItem = function(par1int, par2String, par3int, par4String, par5int) {}
ModPE.setItems = function(par1String) {}
ModPE.setTerrain = function(par1String) {}
ModPE.setUiRenderDebug = function(par1boolean) {}
ModPE.showTipMessage = function(par1String) {
libs_inthis.sendMessage("Tip",par1String)
}
ModPE.takeScreenshot = function(par1String) {}


    Player.addExp = function( par){
          libs_log("Game.I", "增加玩家经验  " + par);
       }

    Player.addItemCreativeInv = function( id, num, data){
        var str = "添加进创造背包  ：" +
                "  ID(int)  "+id+
                "  数量(int)  "+num+
                "  特殊值(int)  "+data;
        libs_log("Game.I", str);
    }
    Player.addItemInventory = function( id, num, data){
        var str = "添加进生存背包  ：" +
                "  ID(int)  "+id+
                "  数量(int)  "+num+
                "  特殊值(int)  "+data;
        libs_log("Game.I", str);
    }

    Player.canFly = function(){
        return false;
    }

    Player.clearInventorySlot = function( slot){
        libs_log("Game.I", "清除玩家背包中的格子    " + slot);
    }

    Player.enchant = function( slot, id, level){
        return true;
    }

    Player.getArmorSlot = function( slot){return 101;}

    Player.getArmorSlotDamage = function( slot){return 100;}

    Player.getCarriedItem = function(){return 89;}

    Player.getCarriedItemCount = function(){return 64;}

    Player.getCarriedItemData = function(){return 1;}

    Player.getDimension = function(){return 1;}

    Player.getEnchantments = function( slot){return [1,1];}

    Player.getEntity = function(){
        return 999;
    }

    Player.getExhaustion = function(){return  3;}

    Player.getExp = function(){return  99999999;}

    Player.getHunger = function(){return 10;}

    Player.getInventorySlot = function( par){
        return 89;
    }

    Player.getInventorySlotCount = function(){
        return 64;
    }

    Player.getInventorySlotData = function(){return 1;}

    Player.getItemCustomName = function( id){return "Stive#滑稽";}

    Player.getLevel = function(){
        return 0;
    }

    Player.getName = function( entity){
        if (entity!=999){
            return "not a player";
        }
        return entity+"";
    }

    Player.getPointedBlockData = function(){
        return 1;
    }

    Player.getPointedBlockId = function(){return 3;}

    Player.getPointedBlockSide = function(){return 4;}

    Player.getPointedBlockX = function(){return 100;}
    Player.getPointedBlockY = function(){return 100;}
    Player.getPointedBlockZ = function(){return 100;}

    Player.getPointedEntity = function(){return 101;}

    Player.getPointedVecX = function(){return 100.85;}
    Player.getPointedVecY = function(){return 100.85;}
    Player.getPointedVecZ = function(){return 100.85;}

    Player.getSaturation = function(){return 10;}

    Player.getScore = function(){return 20;}

    Player.getSelectedSlotId = function(){return 0;}

    Player.getX = function(){return 98.99999999999999997777777744444444444;}
    Player.getY = function(){return 98.99999999999999997777777744444444444;}
    Player.getZ = function(){return 98.99999999999999997777777744444444444;}

    Player.isFlying = function(){return false;}

    Player.isPlayer = function( entity){
        if (entity==999)
            return true;
        else
            return false;
    }

    Player.setArmorSlot = function( slot, id, data){
        var str ="设置盔甲  ：" +
                "  盔甲格  "+slot+
                "  ID  " +id+
                "  特殊值  "+data;
        libs_log("Game.I",str);
    }

    Player.setCanFly = function( fly){
       libs_log("Game.I","设置玩家飞行能力 　"+(fly?"允许飞行":"禁止飞行"));
    }
    Player.setCanFly = function( fly){
        if(fly!=0&&fly!=1){
            libs_log("Game.I",java.lang.String.format("设置玩家飞行能力 　越界的变量  s%，无效果",fly));
        }else{
        libs_log("Game.I","设置玩家飞行能力 　"+(fly==1?"允许飞行":"禁止飞行"));}
    }

    Player.setExhaustion = function( par){
        libs_log("Game.I","设置玩家疲劳值 　"+par);
    }
    Player.setExp = function( par){
        libs_log("Game.I","设置玩家经验 　"+par);
    }
    Player.setFlying = function( par){
       libs_log("Game.I","设置玩家飞行状态 　"+(par?"正在飞行":"未飞行"));
    }
    Player.setHealth = function( par){
        libs_log("Game.I","设置玩家生命值 　"+par);
    }
    Player.setHunger = function( par){
        libs_log("Game.I","设置玩家生命值 　"+par);
    }

    Player.setInventorySlot = function( slot, id, num, data){
        var str = "设定玩家指定物品栏的物品  ：" +
                "  物品栏格子数  ：" +slot+
                "  ID  ：" +id+
                "  数量  ：" +num+
                "  特殊值  ：" +data;
        libs_log("Game.I",str);
    }

    Player.setItemCustomName = function( id, name){
        var str = "设定物品自定义名称  ：" +
                "  ID  "+id+
                "  名字  "+name;
        libs_log("Game.I",str);
    }

    Player.setLevel = function( level){
        libs_log("Game.I","设置玩家等级 　"+level);
    }

    Player.setSelectedSlotId = function( level){
        libs_log("Game.I","设定玩家正在使用的物品在背包的格数 　"+level);
    }

    Player.setSaturation = function( level){
        libs_log("Game.I","设置玩家饱和度　"+level);
    }
    
    
function addItemInventory(par1int, par2int, par3int){}

 function bl_setMobSkin(par1Object, par2String){}

 function bl_spawnMob(par1double, par2double, par3double, par4int, par5String){}

 function explode(par1double, par2double, par3double, par4double, par5boolean){}

 function getCarriedItem(){return 89}

 function getLevel(){return 0}

 function getPitch(par1Object){return 45.0}

 function getPlayerEnt(){return 999}

 function getPlayerX(){return 98.994}

 function getPlayerY(){return 98.994}

 function getPlayerZ(){return 98.994}

 function getTile(par1int, par2int, par3int){return 89}

 function getYaw(par1Object){return 45.0}

 function preventDefault(){
libs_log("Game.I","阻止原有动作发生!");
 }

 function rideAnimal(par1Object, par2Object){}

 function setNightMode(par1boolean){}

 function setPosition(par1Object, par2double, par3double, par4double){}

 function setPositionRelative(par1Object, par2double, par3double, par4double){}

 function setRot(par1Object, par2double, par3double){}

 function setTile(par1int, par2int, par3int, par4int, par5int){}

 function setVelX(par1Object, par2double){}

 function setVelY(par1Object, par2double){}

 function setVelZ(par1Object, par2double){}

 function spawnChicken(par1double, par2double, par3double, par4String){}

 function spawnCow(par1double, par2double, par3double, par4String){}

 function spawnPigZombie(par1double, par2double, par3double, par4int, par5String){}


    var Renderer_bw;
    var Renderer_Class;
    Renderer.get = function( par){
        return new Renderer.renderer();

    }
    Renderer.createHumanoidRenderer = function(){return new Renderer.renderer();}
    Renderer.renderer = function(){
            this.renderType = function(){}
            this.getModel = function(){return new Renderer.model();}
               }
   

    Renderer.model = function(){
            this.getPart = function( name){
            Renderer_bw = name;
            Renderer_Class = new Renderer.modelPart();
            return Renderer_Class;
        }
    }
  Renderer.modelPart = function(){
       this.setTextureOffset = function( par1, par2, par3){
           var str = "设置该模型部位的纹理在皮肤文件中的位置  :" +
                   "  贴图的X轴位置  " +par1+
                   "  贴图的Y轴位置  " +par2+
                   "  是否透明  "+(par3?"透明":"不透明");
           libs_log("Game.I",str);
       }
        this.setTextureOffset = function( par1, par2){
            var str = "设置该模型部位的纹理在皮肤文件中的位置  :" +
                    "  贴图的X轴位置  " +par1+
                    "  贴图的Y轴位置  " +par2+
                    "  是否透明  "+"透明";
            libs_log("Game.I",str);
        }
        this.addBox = function( par1int, par2int, par3int, par4int, par5int, par6int, par7double){
            var str = "在这个模型部位上添加一个新的块  :" +
                    "  X轴起始位置  " +par1int+
                    "  Y轴起始位置  " +par2int+
                    "  Z轴起始位置  " +par3int+
                    "  宽度(X)  " +par4int+
                    "  高度(Y)  " +par5int+
                    "  深度(Z)  " +par6int+
                    "  放大比  "+par7double;
            libs_log("Game.I",str);
        }
        this.addBox = function( par1int, par2int, par3int, par4int, par5int, par6int){
            var str = "在这个模型部位上添加一个新的块  :" +
                    "  X轴起始位置  " +par1int+
                    "  Y轴起始位置  " +par2int+
                    "  Z轴起始位置  " +par3int+
                    "  宽度(X)  " +par4int+
                    "  高度(Y)  " +par5int+
                    "  深度(Z)  " +par6int+
                    "  放大比  0";
            libs_log("Game.I",str);
        }
        this.clear = function(){
            libs_log("Game.I","清除模型部位  " +Renderer_bw);
            return Renderer_Class;
        }

        this.setTextureSize = function( par1int, par2int){
            var str = "设置材质尺寸  :" +
                    "  宽度  " +par1int+
                    "  高度  "+par2int;
            libs_log("Game.I",str);
        }

        this.setRotationPoint = function( par1int, par2int, par3int){
            var str = "设置这个模型部位旋转的中心点  :" +
                    "  X坐标  " +par1int+
                    "  Y坐标  "+par2int+
                    "  Z坐标  "+par3int;
            libs_log("Game.I",str);
        }
}

Server.getAddress = function() {
	return null;
}
Server.getAllPlayerNames = function() {
	return ["Steve"];
}
Server.getAllPlayers = function() {
	return [new java.lang.Long(999)];
}
Server.getPort = function() {
	return 0;
}
Server.joinServer = function(par1String, par2int) {
	libs_log("Game.E", "CJS尚不支持加入服务器");
}
Server.sendChat = function(msg) {
	libs_log("Game.I", "向服务器发送消息：" + msg);
}

ArmorType.boots = 500007;
ArmorType.chestplate = 500008;
ArmorType.helmet = 500009;
ArmorType.leggings = 500010;

BlockFace.DOWN = 0;
BlockFace.EAST = 1;
BlockFace.NORTH = 2;
BlockFace.SOUTH = 3;
BlockFace.UP = 4;
BlockFace.WEST = 5;

    BlockRenderLayer.alpha = 1100007;
    BlockRenderLayer.alpha_seasons = 1100008;
    BlockRenderLayer.alpha_single_side = 1100009;
    BlockRenderLayer.blend = 1100010;
    BlockRenderLayer.doubleside = 1100011;
    BlockRenderLayer.far = 1100012;
    BlockRenderLayer.opaque = 1100013;
    BlockRenderLayer.opaque_seasons = 1100014;
    BlockRenderLayer.seasons_far = 1100015;
    BlockRenderLayer.seasons_far_alpha = 1100016;
    BlockRenderLayer.water = 1100017;


    ChatColor.AQUA = 0xff12b6e5;
    ChatColor.BEGIN = 0xff98A8AD;
    ChatColor.BLACK = android.graphics.Color.BLACK;
    ChatColor.BLUE = android.graphics.Color.BLUE;
    ChatColor.BOLD = 0xff98A8AD;
    ChatColor.DARK_AQUA = 0xff505456;
    ChatColor.DARK_BLUE = 0xff014aa4;
    ChatColor.DARK_GRAY = 0xff747474;
    ChatColor.DARK_GREEN = 0xff368239;
    ChatColor.DARK_PURPLE = 0xff9a0eb5;
    ChatColor.DARK_RED = 0xffa40000;
    ChatColor.GOLD = 0xffF5E406;
    ChatColor.GRAY = android.graphics.Color.GRAY;
    ChatColor.GREEN = android.graphics.Color.GREEN;
    ChatColor.RED = android.graphics.Color.RED;
    ChatColor.RESET = 0x88ffffff;
    ChatColor.WHITE = android.graphics.Color.WHITE;
    ChatColor.YELLOW = android.graphics.Color.YELLOW;
    ChatColor.LIGHT_PURPLE = 0xffd859f1;


    DimensionId.NETHER = 700007;
    DimensionId.NORMAL = 700008;
    
    
    Enchantment.AQUA_AFFINITY = 900007;
    Enchantment.BANE_OF_ARTHROPODS = 900008;
    Enchantment.BLAST_PROTECTION = 900009;
    Enchantment.DEPTH_STRIDER = 900010;
    Enchantment.EFFICIENCY = 900011;
    Enchantment.FEATHER_FALLING = 900012;
    Enchantment.FIRE_ASPECT = 900013;
    Enchantment.FIRE_PROTECTION = 900014;
    Enchantment.FLAME = 900015;
    Enchantment.FORTUNE = 900016;
    Enchantment.INFINITY = 900017;
    Enchantment.KNOCKBACK = 900018;
    Enchantment.LOOTING = 900019;
    Enchantment.LUCK_OF_THE_SEA = 900020;
    Enchantment.LURE = 900021;
    Enchantment.POWER = 900022;
    Enchantment.PROJECTILE_PROTECTION = 900023;
    Enchantment.PROTECTION = 900024;
    Enchantment.PUNCH = 900025;
    Enchantment.RESPIRATION = 900026;
    Enchantment.SHARPNESS = 900027;
    Enchantment.SILK_TOUCH = 900028;
    Enchantment.SMITE = 900029;
    Enchantment.THORNS = 900030;
    Enchantment.UNBREAKING = 900031;
    
    EnchantType.all = 1000007;
    EnchantType.axe = 1000008;
    EnchantType.book = 1000009;
    EnchantType.bow = 1000010;
    EnchantType.fishingRod = 1000011;
    EnchantType.flintAndSteel = 1000012;
    EnchantType.hoe = 1000013;
    EnchantType.pickaxe = 1000014;
    EnchantType.shears = 1000015;
    EnchantType.shovel = 1000016;
    EnchantType.weapon = 1000017;
    
    
    EntityRenderType.arrow = 300007;
    EntityRenderType.bat = 300008;
    EntityRenderType.blaze = 300009;
    EntityRenderType.boat = 300010;
    EntityRenderType.camera = 300011;
    EntityRenderType.chicken = 300012;
    EntityRenderType.cow = 300013;
    EntityRenderType.creeper = 300014;
    EntityRenderType.egg = 300015;
    EntityRenderType.enderman = 300016;
    EntityRenderType.expPotion = 300017;
    EntityRenderType.experienceOrb = 300018;
    EntityRenderType.fallingTile = 300019;
    EntityRenderType.fireball = 300020;
    EntityRenderType.fishHook = 300021;
    EntityRenderType.ghast = 300023;
    EntityRenderType.human = 300024;
    EntityRenderType.ironGolem = 300025;
    EntityRenderType.item = 300026;
    EntityRenderType.lavaSlime = 300027;
    EntityRenderType.lightningBolt = 300028;
    EntityRenderType.map = 300029;
    EntityRenderType.minecart = 300030;
    EntityRenderType.mushroomCow = 300031;
    EntityRenderType.ocelot = 300032;
    EntityRenderType.painting = 300033;
    EntityRenderType.pig = 300034;
    EntityRenderType.player = 300035;
    EntityRenderType.rabbit = 300036;
    EntityRenderType.sheep = 300037;
    EntityRenderType.silverfish = 300038;
    EntityRenderType.skeleton = 300039;
    EntityRenderType.slime = 300040;
    EntityRenderType.smallFireball = 300041;
    EntityRenderType.snowGolem = 300042;
    EntityRenderType.snowball = 300043;
    EntityRenderType.spider = 300044;
    EntityRenderType.squid = 300045;
    EntityRenderType.thrownPotion = 300046;
    EntityRenderType.tnt = 300047;
    EntityRenderType.unknownItem = 300048;
    EntityRenderType.villager = 300049;
    EntityRenderType.villagerZombie = 300050;
    EntityRenderType.witch = 300051;
    EntityRenderType.wolf = 300052;
    EntityRenderType.zombie = 300053;
    EntityRenderType.zombiePigman = 300054;


    EntityType.ARROW = 200007;
    EntityType.BAT = 200008;
    EntityType.BLAZE = 200009;
    EntityType.BOAT = 200010;
    EntityType.CAVE_SPIDER = 200011;
    EntityType.CHICKEN = 200012;
    EntityType.COW = 200013;
    EntityType.CREEPER = 200014;
    EntityType.EGG = 200015;
    EntityType.ENDERMAN = 200016;
    EntityType.EXPERIENCE_ORB = 200017;
    EntityType.EXPERIENCE_POTION = 200018;
    EntityType.FALLING_BLOCK = 200019;
    EntityType.FIREBALL = 200020;
    EntityType.FISHING_HOOK = 200021;
    EntityType.GHAST = 200022;
    EntityType.IRON_GOLEM = 200023;
    EntityType.ITEM = 200024;
    EntityType.LAVA_SLIME = 200025;
    EntityType.LIGHTNING_BOLT = 200026;
    EntityType.MINECART = 200027;
    EntityType.MUSHROOM_COW = 200028;
    EntityType.OCELOT = 200029;
    EntityType.PAINTING = 200030;
    EntityType.PIG = 200031;
    EntityType.PIG_ZOMBIE = 200032;
    EntityType.PLAYER  = 200033;
    EntityType.PRIMED_TNT = 200034;
    EntityType.RABBIT  = 200035;
    EntityType.SHEEP = 200036;
    EntityType.SILVERFISH = 200037;
    EntityType.SKELETON = 200038;
    EntityType.SLIME = 200039;
    EntityType.SMALL_FIREBALL = 200040;
    EntityType.SNOWBALL = 200041;
    EntityType.SNOW_GOLEM = 200042;
    EntityType.SPIDER  = 200043;
    EntityType.SQUID = 200044;
    EntityType.THROWN_POTION = 200045;
    EntityType.VILLAGER = 200046;
    EntityType.WOLF = 200047;
    EntityType.ZOMBIE = 200048;
    EntityType.ZOMBIE_VILLAGER = 200049;


    ItemCategory.DECORATION = 0 ;
    ItemCategory.FOOD = 1 ;
    ItemCategory.INTERNAL = 2 ;
    ItemCategory.MATERIAL = 3 ;
    ItemCategory.TOOL = 4 ;
    
    
    MobEffect.absorption = 60007;
    MobEffect.blindness = 60008;
    MobEffect.confusion = 60009;
    MobEffect.damageBoost = 60010;
    MobEffect.damageResistance = 60011;
    MobEffect.digSlowdown = 60012;
    MobEffect.digSpeed = 60013;
    MobEffect.effectIds = 60014;
    MobEffect.fireResistance = 60015;
    MobEffect.harm = 60016;
    MobEffect.heal = 60017;
    MobEffect.healthBoost = 60018;
    MobEffect.hunger = 60019;
    MobEffect.invisibility = 60020;
    MobEffect.jump = 60021;
    MobEffect.movementSlowdown = 60022;
    MobEffect.movementSpeed = 60023;
    MobEffect.nightVision = 60024;
    MobEffect.poison = 60025;
    MobEffect.regeneration = 60026;
    MobEffect.saturation = 60027;
    MobEffect.waterBreathing = 60028;
    MobEffect.weakness = 60029;
    MobEffect.wither = 60030;
    
    
    ParticleType.rainSplash = 100007;
    ParticleType.redstone = 100008;
    ParticleType.slime = 100009;
    ParticleType.smoke = 100010;
    ParticleType.smoke2 = 100011;
    ParticleType.snowballpoof = 100012;
    ParticleType.spell = 100013;
    ParticleType.spell2 = 100014;
    ParticleType.spell3 = 100015;
    ParticleType.splash = 100016;

    ParticleType.suspendedTown = 100017;
    ParticleType.terrain = 100018;
    ParticleType.waterWake = 100019;

    ParticleType.angryVillager = 100020;
    ParticleType.bubble = 100021;
    ParticleType.cloud = 100022;
    ParticleType.crit = 100023;
    ParticleType.dripLava = 100024;
    ParticleType.dripWater = 100025;
    ParticleType.enchantmenttable = 100026;
    ParticleType.fallingDust = 100027;
    ParticleType.flame = 100028;
    ParticleType.happyVillager = 100029;

    ParticleType.heart = 100030;
    ParticleType.hugeexplosion = 100031;
    ParticleType.hugeexplosionSeed = 100032;
    ParticleType.ink = 100033;
    ParticleType.itemBreak = 100034;
    ParticleType.largeexplode = 100035;
    ParticleType.lava = 100036;
    ParticleType.mobFlame = 100037;
    ParticleType.note = 100038;
    ParticleType.portal = 100039;
    
    
    UseAnimation.bow = 800007;
    UseAnimation.normal = 800008;

 Block = Object.seal(Block)
 Entity = Object.seal(Entity)
 Item = Object.seal(Item)
 Level = Object.seal(Level)
 ModPE = Object.seal(ModPE)
 Player = Object.seal(Player)
 Renderer = Object.seal(Renderer)
 Server = Object.seal(Server)
 ArmorType = Object.seal(ArmorType)
 BlockFace = Object.seal(BlockFace)
 BlockRenderLayer = Object.seal(BlockRenderLayer)
 ChatColor = Object.seal(ChatColor)
 DimensionId = Object.seal(DimensionId)
 Enchantment = Object.seal(Enchantment)
 EnchantType = Object.seal(EnchantType)
 EntityRenderType = Object.seal(EntityRenderType)
 EntityType = Object.seal(EntityType)
 ItemCategory = Object.seal(ItemCategory)
 MobEffect = Object.seal(MobEffect)
 ParticleType = Object.seal(ParticleType)
 UseAnimation = Object.seal(UseAnimation)


