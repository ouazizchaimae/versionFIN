import React from 'react';
import {KeyboardAvoidingView, Platform, SafeAreaView} from 'react-native';
import {globalStyle} from '../../../../../../shared/globalStyle';

import ExpeditionProduitAdminTabNavigation from '../../../../../../navigation/admin/expedition/ExpeditionProduitAdminTabNavigation';

const ExpeditionProduitAdmin = () => {
  return (
    <SafeAreaView style={globalStyle.myContainer}>

      <KeyboardAvoidingView
        behavior={Platform.OS === 'ios' ? 'padding' : 'height'}
        style={{ flex: 1 }} >
        <ExpeditionProduitAdminTabNavigation />
      </KeyboardAvoidingView>
    </SafeAreaView>
  );
};

export default ExpeditionProduitAdmin;
